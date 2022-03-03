package com.noseryoung.uek223.domain.authority;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
    Authority findByName(String name);

    //Used for assigning all authorities to the ADMIN
    @Query(value = "SELECT * FROM authority a WHERE a.name LIKE '%OWN%'",
            nativeQuery = true)
    List<Authority> findOwnAuthorities();
}
