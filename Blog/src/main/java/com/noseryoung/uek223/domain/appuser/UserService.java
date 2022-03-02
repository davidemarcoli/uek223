package com.noseryoung.uek223.domain.appuser;


import com.noseryoung.uek223.domain.appUser.dto.CreateUserDTO;
import com.noseryoung.uek223.domain.exceptions.InvalidEmailException;
import com.noseryoung.uek223.domain.exceptions.NoAccessException;
import com.noseryoung.uek223.domain.role.Role;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User saveUser(com.noseryoung.uek223.domain.appUser.dto.CreateUserDTO user) throws InstanceAlreadyExistsException, InvalidEmailException;

    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    Optional<User> findById(UUID id) throws InstanceNotFoundException, NoAccessException;

    List<User> findAll();

    void deleteUser(UUID id) throws InstanceNotFoundException, NoAccessException;

    User updateUser(User user, UUID id) throws InstanceAlreadyExistsException, InvalidEmailException, NoAccessException, InstanceNotFoundException;

}
