package com.evently.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evently.service.InscricaoService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class InscricaoController extends BaseController {

    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping("/inscricoes")
    public String inscricoes(Model model) {

        model.addAttribute("inscricoes", inscricaoService.getAllInscricoes());
        return "inscricoes";
    }

    @GetMapping("/inscrever/{eventoId}")
    public String salvarEvento(@PathVariable Long eventoId, HttpServletRequest request) {

        String nomeUsuarioLogado = request.getUserPrincipal().getName();

        LocalDate dataInscricao = LocalDate.now();

        inscricaoService.salvar(eventoId, dataInscricao, nomeUsuarioLogado);

        return "redirect:/inscricoes";
    }

}
