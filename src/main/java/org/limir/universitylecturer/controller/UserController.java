package org.limir.universitylecturer.controller;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.UserRequest;
import org.limir.universitylecturer.dto.UserResponse;
import org.limir.universitylecturer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("user/{name}")
    public ResponseEntity<UserResponse> findUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.findUserByName(name));
    }
}
