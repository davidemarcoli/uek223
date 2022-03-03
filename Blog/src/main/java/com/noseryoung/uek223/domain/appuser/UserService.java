package com.noseryoung.uek223.domain.appuser;


import com.noseryoung.uek223.domain.appuser.dto.CreateUserDTO;
import com.noseryoung.uek223.domain.exceptions.InvalidEmailException;
import com.noseryoung.uek223.domain.exceptions.NoAccessException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser(CreateUserDTO user) throws InstanceAlreadyExistsException, InvalidEmailException;

    User updateAndSaveUser(User user) throws InstanceAlreadyExistsException, InvalidEmailException;

    Optional<User> findById(UUID id) throws InstanceNotFoundException, NoAccessException;

    List<User> findAll();

    void deleteUser(UUID id) throws InstanceNotFoundException, NoAccessException;

    User updateUser(User user, UUID id) throws InstanceAlreadyExistsException, InvalidEmailException, NoAccessException, InstanceNotFoundException;

}
