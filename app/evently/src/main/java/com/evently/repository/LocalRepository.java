package com.evently.repository;

import com.evently.repository.entity.LocalEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<LocalEvento, Long> {

    List<LocalEvento> findByOrganizadorId(Long organizadorId);

    List<LocalEvento> findByNomeContaining(String nome);
}
