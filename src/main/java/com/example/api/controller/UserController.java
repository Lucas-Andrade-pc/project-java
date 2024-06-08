package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Create users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userService.createUsers(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @GetMapping("/getUsers")

    public ResponseEntity<List<User>> getUsers(){
        List<User> getUsers = userService.getUsers();
        return ResponseEntity.ok(getUsers);
    }

}
