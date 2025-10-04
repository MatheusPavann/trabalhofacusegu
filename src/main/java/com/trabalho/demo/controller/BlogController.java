package com.trabalho.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @GetMapping("/biologia")
    public String biologia() {
        return "Página restrita para leitores de Biologia!";
    }

    @GetMapping("/matematica")
    public String matematica() {
        return "Página restrita para leitores de Matemática!";
    }
}
