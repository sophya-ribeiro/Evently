package com.evently.repository;

import com.evently.repository.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);

    boolean existsByUsuario(String usuario);

    Optional<Usuario> findByNome(String nome);
}
