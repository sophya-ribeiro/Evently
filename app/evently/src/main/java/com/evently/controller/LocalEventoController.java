package com.evently.controller;

import com.evently.service.LocalEventoService;

import jakarta.servlet.http.HttpServletRequest;

import com.evently.repository.entity.LocalEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/meus-locais")
public class LocalEventoController extends BaseController {

    @Autowired
    private LocalEventoService localEventoService;

    @GetMapping
    public String listarLocais(Model model) {
        List<LocalEvento> locais = localEventoService.getAllLocais();
        model.addAttribute("locais", locais);
        System.out.println(locais);
        return "locais";
    }

    @PostMapping("/salvar")
    public String salvarLocal(
            @RequestParam(name = "nome") String nome,
            @RequestParam(name = "endereco") String endereco,
            @RequestParam(name = "capacidade") int capacidade,
            HttpServletRequest request) {

        String nomeUsuarioLogado = request.getUserPrincipal().getName();

        localEventoService.salvar(
                nome,
                endereco,
                capacidade,
                nomeUsuarioLogado);

        return "redirect:/meus-locais";
    }

    @PostMapping("/atualizar")
    public String atualizar(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "nome") String nome,
            @RequestParam(name = "endereco") String endereco,
            @RequestParam(name = "capacidade") int capacidade
    ) {
        localEventoService.atualizar(
                id,
                nome,
                endereco,
                capacidade);

        return "redirect:/meus-locais";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLocal(@PathVariable Long id, Model model) {
        String mensagem = localEventoService.excluir(id);
        model.addAttribute("mensagem", mensagem);
        return "redirect:/meus-locais";
    }

}
