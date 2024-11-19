package com.evently.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/meus-locais")
    public String locais() {
        return "locais";
    }
}
