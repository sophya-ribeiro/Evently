package com.evently.service;

import com.evently.repository.LocalEventoRepository;
import com.evently.repository.OrganizadorRepository;
import com.evently.repository.UsuarioRepository;
import com.evently.repository.entity.LocalEvento;
import com.evently.repository.entity.Organizador;
import com.evently.repository.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalEventoService {

    @Autowired
    private LocalEventoRepository localEventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    public List<LocalEvento> getAllLocais() {
        return localEventoRepository.findAll();
    }

    public Optional<LocalEvento> getLocalById(Long id) {
        return localEventoRepository.findById(id);
    }

    public String salvar(String nome, String endereco, Integer capacidade, String nomeUsuarioLogado) {
        if (localEventoRepository.existsByNome(nome)) {
            return "Já existe um local com este nome.";
        }

        Usuario usuario = usuarioRepository.findByUsuario(nomeUsuarioLogado);

        Optional<Organizador> organizadorOptional = organizadorRepository.findByUsuarioId(usuario.getId());

        if (organizadorOptional.isPresent()) {
            Organizador organizador = organizadorOptional.get();

            LocalEvento localEvento = new LocalEvento (nome, endereco, capacidade, organizador);
            localEventoRepository.save(localEvento);

            return "Local salvo com sucesso!";
        }

        return "Não foi possível salvar o local.";
    }

    public String atualizar(Long id, String nome, String endereco, Integer capacidade) {
        Optional<LocalEvento> localOptional = localEventoRepository.findById(id);

        if (localOptional.isPresent()) {
            LocalEvento localEvento = localOptional.get();
            localEvento.setNome(nome);
            localEvento.setEndereco(endereco);
            localEvento.setCapacidade(capacidade);

            localEventoRepository.save(localEvento);
            return "Local atualizado com sucesso!";
        }

        return "Local não encontrado.";
    }

    public String excluir(Long id) {
        if (localEventoRepository.existsById(id)) {
            localEventoRepository.deleteById(id);
            return "Local excluído com sucesso!";
        }
        return "Local não encontrado.";
    }
}
