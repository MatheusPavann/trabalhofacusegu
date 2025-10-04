package com.trabalho.demo.controller;

import com.trabalho.demo.model.User;
import com.trabalho.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String role) {
        return userService.register(username, password, role);
    }
}
