package com.trabalho.demo.controller;

import com.trabalho.demo.dto.LoginRequest;
import com.trabalho.demo.dto.LoginResponse;
import com.trabalho.demo.model.User;
import com.trabalho.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request.getUsername(), request.getPassword());

        if (user == null) {
            return new LoginResponse(false, null, "Usuário ou senha inválidos");
        }

        return new LoginResponse(true, user.getRole(), null);
    }
}
