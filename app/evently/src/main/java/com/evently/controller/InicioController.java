package com.evently.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.evently.service.EventoService;
import com.evently.service.LocalEventoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class InicioController extends BaseController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private LocalEventoService localEventoService;

    @GetMapping("/")
    public String inicio(Model model, HttpServletRequest request) {
        String nomeUsuarioLogado = request.getUserPrincipal().getName();

        model.addAttribute("eventos", eventoService.getAllEvents());

        if (!userService.verificaOrganizador(nomeUsuarioLogado)) {
            return "inicio-participante";
        }

        model.addAttribute("locais", localEventoService.getAllLocais());

        return "inicio";
    }

    @GetMapping("/participante")
    public String inicioParticipante() {
        return "inicio-participante";
    }
}
