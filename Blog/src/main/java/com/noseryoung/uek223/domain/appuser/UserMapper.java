package com.noseryoung.uek223.domain.appuser;

import com.noseryoung.uek223.domain.appuser.dto.CreateUserDTO;
import com.noseryoung.uek223.domain.appuser.dto.UserDTONoSensitive;
import com.noseryoung.uek223.domain.appuser.dto.UserDTOOnlyUsername;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTONoSensitive userToUserDtoNoSensitive(User user);

    User userDtoToUserNoSensitive(UserDTONoSensitive userDto);

    CreateUserDTO userToUserDTOsCreate(User user);

    User userDTOsCreateToUser(CreateUserDTO userDTO);

    UserDTOOnlyUsername userToUserDTOOnlyUsername(User user);

    User userDTOOnlyUsernameToUser(UserDTOOnlyUsername userDTO);
}