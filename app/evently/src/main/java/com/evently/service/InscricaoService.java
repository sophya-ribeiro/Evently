package com.evently.service;

import com.evently.repository.EventoRepository;
import com.evently.repository.InscricaoRepository;
import com.evently.repository.ParticipanteRepository;
import com.evently.repository.UsuarioRepository;
import com.evently.repository.entity.Evento;
import com.evently.repository.entity.Inscricao;
import com.evently.repository.entity.Participante;
import com.evently.repository.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    public List<Inscricao> getAllInscricoes() {
        return inscricaoRepository.findAll();
    }

    public Optional<Inscricao> getEventById(Long id) {
        return inscricaoRepository.findById(id);
    }

    public String salvar(Long eventoId, LocalDate dataInscricao, String nomeUsuarioLogado) {

        Usuario usuario = usuarioRepository.findByUsuario(nomeUsuarioLogado);
        Optional<Participante> participanteOptional = participanteRepository.findByUsuarioId(usuario.getId());
        Optional<Evento> optionalEvento = eventoRepository.findById(eventoId);

        if (participanteOptional.isPresent() && optionalEvento.isPresent()) {
            Participante participante = participanteOptional.get();

            Evento evento = optionalEvento.get();

            Inscricao inscricao = new Inscricao(dataInscricao, participante, evento);
            inscricaoRepository.save(inscricao);

            return "Inscrição salva com sucesso!";
        }

        return "Não foi possível salvar inscrição.";
    }
}
