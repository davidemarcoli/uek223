package com.example.demo.domain.role;

import com.example.demo.domain.exceptions.InvalidEmailException;
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
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "Retrieves all roles")
    @GetMapping("/")
    public ResponseEntity<List<Role>> findAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Creates and saves a new role to the database")
    @PostMapping("/")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) throws InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the existing role corresponding to the UUID and saves it to the database")
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@Valid @RequestBody Role role, @Valid @PathVariable UUID id) {
        return new ResponseEntity<>(roleService.updateRole(role, id), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes the role with the corresponding UUID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@Valid @PathVariable UUID id) throws InstanceNotFoundException {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
