package com.example.kreambidding.controller;

import com.example.kreambidding.controller.dto.UserDTO;
import com.example.kreambidding.model.user.User;
import com.example.kreambidding.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/api/v1/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/v1/users/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/api/v1/users/email/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/api/v1/users")
    public User updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }
}
