package com.evently.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.evently.service.UserService;

public class BaseController {

    @Autowired
    UserService userService;

    @ModelAttribute("nomeUsuarioLogado")
    protected String obterNomeUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = userService.obterNomeUsuarioLogadoPorAuth(auth);

        return nomeUsuario;
    }
}
