package com.noseryoung.uek223.domain.authority;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authority")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;

    @Operation(summary = "Retrieves all authorities")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<Authority>> findAllAuthorities() {
        return new ResponseEntity<>(authorityService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves a specific authorities")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Authority> findAuthorities(@Valid @PathVariable UUID id) {
        return new ResponseEntity<>(authorityService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Creates and saves a new authority to the database")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Authority> createAuthority(@Valid @RequestBody Authority authority) {
        return new ResponseEntity<>(authorityService.saveRole(authority), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the existing authority corresponding to the UUID and saves it to the database")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Authority> updateAuthority(@Valid @RequestBody Authority authority, @Valid @PathVariable UUID id) {
        return new ResponseEntity<>(authorityService.updateRole(authority, id), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes the authority with the corresponding UUID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Authority> deleteAuthority(@Valid @PathVariable UUID id) {
        authorityService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
