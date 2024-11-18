package com.evently.repository;

import com.evently.repository.entity.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByParticipanteId(Long participanteId);

    Optional<Inscricao> findByEventoIdAndParticipanteId(Long eventoId, Long participanteId);

    List<Inscricao> findByEventoId(Long eventoId);
}
