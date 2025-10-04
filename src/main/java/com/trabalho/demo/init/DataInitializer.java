package com.trabalho.demo.init;

import com.trabalho.demo.model.User;
import com.trabalho.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("guilherme").isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String senhaHash = encoder.encode("12345566"); // senha inicial

                User user = new User("guilherme", senhaHash, "ROLE_ADMIN");
                userRepository.save(user);

                System.out.println("Usuário inicial criado: guilherme / senha: 12345566");
            }
        };
    }
}
