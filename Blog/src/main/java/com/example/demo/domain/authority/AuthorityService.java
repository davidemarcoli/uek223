package com.example.demo.domain.authority;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AuthorityService {
    void deleteRole(UUID id);

    Authority updateRole(Authority authority, UUID id);

    Authority saveRole(Authority authority);

    List<Authority> findAll();
}
