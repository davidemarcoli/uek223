package com.noseryoung.uek223.domain.role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    void addAuthorityToRole( String rolename, String authorityname);

    List<Role> findAll();

    Role saveRole(Role role);

    Role updateRole(Role role, UUID id);

    void deleteRole(UUID id);
}
