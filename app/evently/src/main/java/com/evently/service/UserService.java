package com.evently.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evently.repository.OrganizadorRepository;
import com.evently.repository.UsuarioRepository;
import com.evently.repository.entity.Usuario;
import com.evently.repository.entity.Organizador;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario usuarioEncontrado = usuarioRepository.findByUsuario(usuario);

        if (usuarioEncontrado != null) {
            UserDetails usuarioDetalhes = User.withUsername(usuario)
                .password(usuarioEncontrado.getSenha())
                .build();

            return usuarioDetalhes;
        }

        return null;
    }

    public String obterNomeUsuarioLogadoPorAuth(Authentication auth) {
        String usuarioLogado = ((UserDetails) auth.getPrincipal()).getUsername();

        Usuario usuario = usuarioRepository.findByUsuario(usuarioLogado);

        return usuario.getNome();
    }

    public boolean verificaOrganizador(String usuarioLogado) {
        Usuario usuario = usuarioRepository.findByUsuario(usuarioLogado);
        Optional<Organizador> organizadorOptional = organizadorRepository.findByUsuarioId(usuario.getId());
        
        return organizadorOptional.isPresent();
    }
}
