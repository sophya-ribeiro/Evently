package com.evently.repository;

import com.evently.repository.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findById(Long id);

    List<Evento> findByDataInicio(LocalDate dataInicio);

    List<Evento> findByOrganizadorId(Long organizadorId);

}
