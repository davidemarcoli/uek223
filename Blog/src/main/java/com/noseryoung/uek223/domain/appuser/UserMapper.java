package com.noseryoung.uek223.domain.appuser;

import com.noseryoung.uek223.domain.appuser.dto.CreateUserDTO;
import com.noseryoung.uek223.domain.appuser.dto.UserDTO;
import com.noseryoung.uek223.domain.appuser.dto.UserDTONoSensitive;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(User user);
    User userDtoToUser(UserDTO userDto);
    UserDTONoSensitive userToUserDtoNoSensitive(User user);
    User userDtoToUserNoSensitive(UserDTONoSensitive userDto);
    CreateUserDTO userToUserDTOsCreate(User user);
    User userDTOsCreateToUser(CreateUserDTO userDTO);
}