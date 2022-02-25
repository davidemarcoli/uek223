package com.noseryoung.uek223.domain.role;

import com.noseryoung.uek223.domain.authority.Authority;
import com.noseryoung.uek223.domain.authority.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void addAuthorityToRole( String rolename, String authorityname){
    Authority authority = authorityRepository.findByName(authorityname);
    Role role = roleRepository.findByName(rolename);
    role.getAuthorities().add(authority);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role updateRole(Role role, UUID id) {
        role.setId(id);
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }
}

