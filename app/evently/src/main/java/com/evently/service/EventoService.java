package com.evently.service;

import com.evently.repository.EventoRepository;
import com.evently.repository.OrganizadorRepository;
import com.evently.repository.UsuarioRepository;
import com.evently.repository.entity.Evento;
import com.evently.repository.entity.LocalEvento;
import com.evently.repository.entity.Organizador;
import com.evently.repository.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    public List<Evento> getAllEvents() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventById(Long id) {
        return eventoRepository.findById(id);
    }

    public String salvar(String nome, String descricao, LocalDate dataInicio, LocalTime horaInicio, Integer duracao, BigDecimal preco, LocalEvento local, String nomeUsuarioLogado) {
        Usuario usuario = usuarioRepository.findByUsuario(nomeUsuarioLogado);

        Optional<Organizador> organizadorOptional = organizadorRepository.findByUsuarioId(usuario.getId());

        if (organizadorOptional.isPresent()) {
            Organizador organizador = organizadorOptional.get();

            Evento contato = new Evento (nome, descricao, dataInicio, horaInicio, duracao, preco, local, organizador);
            eventoRepository.save(contato);

            return "Contato salvo com sucesso!";
        }

        return "Não foi possível salvar Evento.";
    }

    public String atualizar(Long id, Evento eventoDetalhes) {

        Optional<Evento> optionalEvento = eventoRepository.findById(id);

        if (optionalEvento.isPresent()) {
            Evento evento = optionalEvento.get();
            evento.setNome(eventoDetalhes.getNome());
            evento.setDescricao(eventoDetalhes.getDescricao());
            evento.setData(eventoDetalhes.getData());
            evento.setHora(eventoDetalhes.getHora());
            evento.setDuracao(eventoDetalhes.getDuracao());
            evento.setPreco(eventoDetalhes.getPreco());

            eventoRepository.save(evento);

            return "Evento salvo com sucesso!";
        }
        return "Não foi posssível salvar o evento.";
    }

    public void excluir(Long id) {
        eventoRepository.deleteById(id);
    }
}
