package com.noseryoung.uek223.domain.authority;

import com.noseryoung.uek223.domain.exceptions.InvalidEmailException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authority")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;

    @Operation(summary = "Retrieves all authorities")
    @GetMapping("/")
    public ResponseEntity<List<Authority>> findAllAuthorities() {
        return new ResponseEntity<>(authorityService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Creates and saves a new authority to the database")
    @PostMapping("/")
    public ResponseEntity<Authority> createAuthority(@Valid @RequestBody Authority authority) throws InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<>(authorityService.saveRole(authority), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the existing authority corresponding to the UUID and saves it to the database")
    @PutMapping("/{id}")
    public ResponseEntity<Authority> updateAuthority(@Valid @RequestBody Authority authority, @Valid @PathVariable UUID id) {
        return new ResponseEntity<>(authorityService.updateRole(authority, id), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes the authority with the corresponding UUID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Authority> deleteAuthority(@Valid @PathVariable UUID id) throws InstanceNotFoundException {
        authorityService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
