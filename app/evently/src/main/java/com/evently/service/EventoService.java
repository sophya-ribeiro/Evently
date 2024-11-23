package com.evently.service;

import com.evently.repository.EventoRepository;
import com.evently.repository.LocalEventoRepository;
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

    @Autowired
    private LocalEventoRepository localEventoRepository;

    public List<Evento> getAllEvents() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventById(Long id) {
        return eventoRepository.findById(id);
    }

    public String salvar(String nome, String descricao, LocalDate dataInicio, LocalTime horaInicio, Integer duracao,
            BigDecimal preco, Long localId, String nomeUsuarioLogado) {

        Usuario usuario = usuarioRepository.findByUsuario(nomeUsuarioLogado);
        Optional<Organizador> organizadorOptional = organizadorRepository.findByUsuarioId(usuario.getId());
        Optional<LocalEvento> localOptional = localEventoRepository.findById(localId);

        if (organizadorOptional.isPresent() && localOptional.isPresent()) {
            Organizador organizador = organizadorOptional.get();

            LocalEvento local = localOptional.get();

            Evento evento = new Evento(nome, descricao, dataInicio, horaInicio, duracao, preco, local, organizador);
            eventoRepository.save(evento);

            return "Evento salvo com sucesso!";
        }

        return "Não foi possível salvar Evento.";
    }

    public String atualizar(Long id, String nome, String descricao, LocalDate dataInicio, LocalTime horaInicio,
            Integer duracao, BigDecimal preco, Long localId) {

        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        Optional<LocalEvento> localOptional = localEventoRepository.findById(localId);

        if (optionalEvento.isPresent() && localOptional.isPresent()) {
            Evento evento = optionalEvento.get();
            LocalEvento localEvento = localOptional.get();

            evento.setNome(nome);
            evento.setDescricao(descricao);
            evento.setDataInicio(dataInicio);
            evento.setHoraInicio(horaInicio);
            evento.setDuracao(duracao);
            evento.setPreco(preco);
            evento.setLocal(localEvento);

            eventoRepository.save(evento);

            return "Evento salvo com sucesso!";
        }

        return "Não foi posssível salvar o evento.";
    }

    public void excluir(Long id) {
        eventoRepository.deleteById(id);
    }
}
