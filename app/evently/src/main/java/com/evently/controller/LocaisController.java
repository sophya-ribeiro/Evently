package com.evently.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocaisController {

    @GetMapping("/inicio/meus-locais")
    public String locais() {
        return "locais";
    }
}
