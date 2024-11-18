package com.evently.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evently.repository.UsuarioRepository;
import com.evently.repository.entity.Usuario;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

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
}
