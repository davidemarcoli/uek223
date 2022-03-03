package com.noseryoung.uek223.domain.role;

import com.noseryoung.uek223.domain.authority.Authority;
import com.noseryoung.uek223.domain.authority.AuthorityRepository;
import com.noseryoung.uek223.domain.exceptions.NoBlogPostFoundException;
import com.noseryoung.uek223.domain.role.dto.UpdateRoleDTO;
import com.noseryoung.uek223.domain.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleMapper roleMapper;
    private final NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    @Override
    public void addAuthorityToRole(String rolename, String authorityname) {
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
    @SneakyThrows
    public Role updateRole(UpdateRoleDTO role, UUID id) {
        Role newRole = roleMapper.updateRoleDTOToRole(role);
        Role oldRole = roleRepository.findById(id).orElseThrow(()
                -> new NoBlogPostFoundException("No Role found with the given id"));

        nullAwareBeanUtilsBean.copyProperties(oldRole, newRole);

        return roleRepository.saveAndFlush(oldRole);
    }

    @Override
    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findRoleById(UUID id) {
        return roleRepository.findById(id).orElse(null);
    }
}

