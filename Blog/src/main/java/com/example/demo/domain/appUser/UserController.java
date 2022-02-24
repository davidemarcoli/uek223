package com.example.demo.domain.appUser;


import com.example.demo.domain.exceptions.InvalidEmailException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Retrieves all users")
    @GetMapping("/")
    public ResponseEntity<Collection<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves the user with the corresponding UUID")
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@Valid @PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
    }

    @Operation(summary = "Creates and saves a new user to the database")
    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates the existing user corresponding to the UUID and saves it to the database")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @Valid @PathVariable UUID id) {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletes the user with the corresponding UUID")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@Valid @PathVariable UUID id) throws InstanceNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> handleInstanceAlreadyExistsException(InstanceAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> handleInstanceNotFoundException(InstanceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleEmailException(InvalidEmailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
