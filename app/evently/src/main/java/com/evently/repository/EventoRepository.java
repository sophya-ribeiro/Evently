package com.evently.repository;

import com.evently.repository.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByNome(String nome);

    List<Evento> findByDataInicio(LocalDate dataInicio);

    List<Evento> findByOrganizadorId(Long organizadorId);

    List<Evento> findByLocalId(Long localId);

}
