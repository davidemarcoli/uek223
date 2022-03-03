package com.noseryoung.uek223.domain.authority;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AuthorityService {
    void deleteAuthority(UUID id);

    Authority updateAuthority(Authority authority, UUID id);

    Authority createAuthority(Authority authority);

    List<Authority> findAllAuthorities();

    Authority findAuthorityById(UUID id);
}
