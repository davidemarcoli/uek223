package com.noseryoung.uek223.domain.appUser;

import com.noseryoung.uek223.domain.appUser.dto.CreateUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    CreateUserDTO userToUserDTOsCreate(User user);
    User userDTOsCreateToUser(CreateUserDTO userDTO);
}
