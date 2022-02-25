package com.noseryoung.uek223.domain.authority;

import com.noseryoung.uek223.domain.authority.Authority;
import com.noseryoung.uek223.domain.authority.AuthorityRepository;
import com.noseryoung.uek223.domain.authority.AuthorityService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void deleteRole(UUID id) {
        authorityRepository.deleteById(id);
    }

    @Override
    public Authority updateRole(Authority authority, UUID id) {
        authority.setId(id);
        return authorityRepository.saveAndFlush(authority);
    }

    @Override
    public Authority saveRole(Authority authority) {
        return null;
    }

    @Override
    public List<Authority> findAll() {
        return null;
    }
}
