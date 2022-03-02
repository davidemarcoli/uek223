package com.noseryoung.uek223.domain.appuser;

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

    private final PasswordEncoder passwordEncoder;

    @Override
//    This method is used for security authentication, use caution when changing this
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
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
    public User saveUser(User user) throws InstanceAlreadyExistsException, InvalidEmailException, InstanceNotFoundException {

        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new InvalidEmailException("Email is not valid");
        }

        //When updating a user he needs the possiblity to keep his username ->  != null
        if (userRepository.findByUsername(user.getUsername()) != null &&
                !(userRepository.existsById(user.getId())
                        && user.getUsername().equals(userRepository.findById(user.getId()).orElseThrow(InstanceNotFoundException::new).getUsername()))) {
            throw new InstanceAlreadyExistsException("Username already exists");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
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
            return saveUser(user);
        } else {
            throw new NoAccessException();
        }


    }

    private boolean hasAccess(UUID id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            return id.equals(userRepository.findByUsername(auth.getName()).getId()) ||
                    auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } catch (Exception e) {
            // do not grant access if user couldn't be found/verified
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
