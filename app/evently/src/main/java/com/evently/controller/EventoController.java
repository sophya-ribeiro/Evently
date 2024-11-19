package com.evently.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evently.repository.entity.Evento;
import com.evently.repository.entity.LocalEvento;
import com.evently.repository.entity.Organizador;
import com.evently.service.EventoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @PostMapping("/salvar")
    public String salvarEvento(
            @RequestParam String nome,
            @RequestParam String descricao,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalTime horaInicio,
            @RequestParam Integer duracao,
            @RequestParam BigDecimal preco,
            @RequestParam LocalEvento local,
            HttpServletRequest request
    ) {
        String nomeUsuarioLogado = request.getUserPrincipal().getName();

        eventoService.salvar(nome, descricao, dataInicio, horaInicio, duracao, preco, local, nomeUsuarioLogado);

        return "redirect:/";
    }
}
