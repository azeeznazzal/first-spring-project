package com.abdalazeez.firstspringproject.controller;


import com.abdalazeez.firstspringproject.customExceptions.UserNotFoundException;
import com.abdalazeez.firstspringproject.services.UserService;
import com.abdalazeez.firstspringproject.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getAll() {
        return userService.gerAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@Valid @PathVariable int id) {
        User user = userService.getUser(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/adduser")
    public ResponseEntity<HttpStatus> addUser(@Valid @RequestBody User user) {
        Optional<User> addedUser = userService.addUser(user);

        if (addedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable int id,@Valid @RequestBody User user) throws UserNotFoundException {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser (@Valid @PathVariable int id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
