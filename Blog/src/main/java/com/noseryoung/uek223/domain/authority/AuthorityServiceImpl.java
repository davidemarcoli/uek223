package com.noseryoung.uek223.domain.authority;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public void deleteAuthority(UUID id) {
        authorityRepository.deleteById(id);
    }

    @Override
    public Authority updateAuthority(Authority authority, UUID oldAuthorityId) {
        authority.setId(oldAuthorityId);
        return authorityRepository.saveAndFlush(authority);
    }

    @Override
    public Authority createAuthority(Authority authority) {
        return authorityRepository.saveAndFlush(authority);
    }

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findAuthorityById(UUID id) {
        return authorityRepository.findById(id).orElse(null);
    }
}
