package com.noseryoung.uek223.domain.appUser;


import com.noseryoung.uek223.domain.exceptions.InvalidEmailException;
import com.noseryoung.uek223.domain.role.Role;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User saveUser(User user) throws InstanceAlreadyExistsException, InvalidEmailException;

    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    User getUser(String username);

    Optional<User> findById(UUID id) throws InstanceNotFoundException;

    List<User> findAll();

    User save(User user);

    void deleteUser(UUID id) throws InstanceNotFoundException;

    User updateUser(User user, UUID id);

}
