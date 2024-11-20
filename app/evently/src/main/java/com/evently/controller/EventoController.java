package com.evently.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evently.service.EventoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/salvar")
    public String salvarEvento(
            @RequestParam(name = "nome") String nome,
            @RequestParam(name = "descricao") String descricao,
            @RequestParam(name = "dataInicio") LocalDate dataInicio,
            @RequestParam(name = "horaInicio") LocalTime horaInicio,
            @RequestParam(name = "duracao") Integer duracao,
            @RequestParam(name = "preco") BigDecimal preco,
            @RequestParam(name = "local_id") Long localId,
            HttpServletRequest request) {
        String nomeUsuarioLogado = request.getUserPrincipal().getName();

        eventoService.salvar(nome, descricao, dataInicio, horaInicio, duracao, preco, localId, nomeUsuarioLogado);

        return "redirect:/";
    }

    @PostMapping("/atualizar")
    public String atualizar(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "nome") String nome,
            @RequestParam(name = "descricao") String descricao,
            @RequestParam(name = "dataInicio") LocalDate dataInicio,
            @RequestParam(name = "horaInicio") LocalTime horaInicio,
            @RequestParam(name = "duracao") Integer duracao,
            @RequestParam(name = "preco") BigDecimal preco,
            @RequestParam(name = "local_id") Long localId) {
        eventoService.atualizar(
                id,
                nome,
                descricao,
                dataInicio,
                horaInicio,
                duracao,
                preco,
                localId);

        return "redirect:/";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLocal(@PathVariable Long id, Model model) {
        eventoService.excluir(id);

        return "redirect:/";
    }
}
