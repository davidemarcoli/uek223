package com.noseryoung.uek223.domain.role;

import com.noseryoung.uek223.domain.role.dto.UpdateRoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role updateRoleDTOToRole(UpdateRoleDTO role);
}
