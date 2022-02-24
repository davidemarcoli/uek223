package com.example.demo.domain.appUser;


import com.example.demo.domain.exceptions.InvalidEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/")
    public ResponseEntity<String> HomeTest() {
        return ResponseEntity.ok().body("Hello World");
    }


    @GetMapping("/getAll")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<Collection<User>>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@Valid @PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<User>(userService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> create(@Valid @RequestBody User user) throws InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody User user, @Valid @PathVariable UUID id) {
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@Valid @PathVariable UUID id) throws InstanceNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
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
