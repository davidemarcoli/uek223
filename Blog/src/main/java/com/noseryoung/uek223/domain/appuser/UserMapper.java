package com.noseryoung.uek223.domain.appuser;

import com.noseryoung.uek223.domain.appuser.dto.CreateUserDTO;
import com.noseryoung.uek223.domain.appuser.dto.UserDTONoSensitive;
import com.noseryoung.uek223.domain.appuser.dto.UserDTOOnlyUsername;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //Not sure if these are all needed, but we experienced flakiness without them.
    UserDTONoSensitive userToUserDtoNoSensitive(User user);

    User userDTOToUserNoSensitive(UserDTONoSensitive userDto);

    CreateUserDTO userToUserDTOCreate(User user);

    User userDTOCreateToUser(CreateUserDTO userDTO);

    UserDTOOnlyUsername userToUserDTOOnlyUsername(User user);

    User userDTOOnlyUsernameToUser(UserDTOOnlyUsername userDTO);
}