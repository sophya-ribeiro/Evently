package com.evently.repository;

import com.evently.repository.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {

    Optional<Organizador> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);

    Optional<Organizador> findByEmail(String email);

    Optional<Organizador> findByUsuarioId(Long usuarioId);
}
