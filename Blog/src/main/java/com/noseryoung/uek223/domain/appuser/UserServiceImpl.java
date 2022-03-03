package com.noseryoung.uek223.domain.appuser;

import com.noseryoung.uek223.domain.appuser.dto.CreateUserDTO;
import com.noseryoung.uek223.domain.exceptions.InvalidEmailException;
import com.noseryoung.uek223.domain.exceptions.NoAccessException;
import com.noseryoung.uek223.domain.role.Role;
import com.noseryoung.uek223.domain.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final String[] errorMessages = new String[]{"User not found", "Email is not valid", "Username already exists"};

    @Override
//    This method is used for security authentication, use caution when changing this
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(errorMessages[0]);
        } else {
//          Construct a valid set of Authorities (needs to implement Granted Authorities)
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                role.getAuthorities().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
            });
//            return a spring internal user object that contains authorities and roles
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .forEach(authorities::add);
        }
        return authorities;
    }

    @Override
    public User saveUser(CreateUserDTO userDTO) throws InstanceAlreadyExistsException, InvalidEmailException {
        if (!EmailValidator.getInstance().isValid(userDTO.getEmail())) {
            throw new InvalidEmailException(errorMessages[1]);
        }
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new InstanceAlreadyExistsException(errorMessages[2]);
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        //Set default role of every new user to USER
        User user = userMapper.userDTOsCreateToUser(userDTO);
        user.setRoles(List.of(roleRepository.findByName("USER")));
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public User updateAndSaveUser(User user) throws InstanceAlreadyExistsException, InvalidEmailException {
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new InvalidEmailException("Email is not valid");
        }
        //When updating a user he needs the possibility to keep his username, but in case he changes it we need to
        // check if it's already in use
        if (userRepository.findByUsername(user.getUsername()) != null &&  /* true = username maybe updated*/
                /*true = username does not belong to updated profile */
                !user.getUsername().equals(userRepository.findById(user.getId()).get().getUsername())) {
            throw new InstanceAlreadyExistsException(errorMessages[2]);
        }
        // If password is updated -> encrypt, else -> do nothing
        if (!(passwordEncoder.matches(/* Maybe updated password */ user.getPassword(),
                /* Old password */ userRepository.findById(user.getId()).get().getPassword()))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    //TODO:Extract to roleservice
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }


    @Override
    public Optional<User> findById(UUID id) throws NoAccessException {
        if (hasAccess(id)) {
            return userRepository.findById(id);
        } else {
            throw new NoAccessException();
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) throws NoAccessException {
        if (hasAccess(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NoAccessException();
        }
    }

    @Override
    public User updateUser(User user, UUID id) throws InstanceAlreadyExistsException, InvalidEmailException, NoAccessException, InstanceNotFoundException {
        if (hasAccess(id)) {
            user.setId(id);
            user.setRoles(userRepository.findById(id).orElseThrow(InstanceNotFoundException::new).getRoles());
            return updateAndSaveUser(user);
        } else {
            throw new NoAccessException();
        }


    }

    private boolean hasAccess(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            // if user is requesting his own profile return true
            return id.equals(userRepository.findByUsername(auth.getName()).getId()) ||
                    auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } catch (Exception e) {
            // do not grant access if user couldn't be found/verified to prevent giving a potential attacker
            // information
            return false;
        }
    }

    private boolean hasAccess(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            return username.equals(auth.getName()) ||
                    auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } catch (Exception e) {
            // do not grant access if user couldn't be verified
            return false;
        }

    }

}
