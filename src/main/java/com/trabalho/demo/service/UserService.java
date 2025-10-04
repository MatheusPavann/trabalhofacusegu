package com.trabalho.demo.service;

import com.trabalho.demo.model.User;
import com.trabalho.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrar usuário
    public User register(String username, String rawPassword, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Usuário já existe!");
        }

        User user = new User(username, passwordEncoder.encode(rawPassword), role);
        return userRepository.save(user);
    }

    // Autenticar usuário
    public User authenticate(String username, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
                return user;
            }
        }
        return null;
    }
}
