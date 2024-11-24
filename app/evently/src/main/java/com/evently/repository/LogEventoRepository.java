package com.evently.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evently.repository.entity.LogEvento;

@Repository
public interface LogEventoRepository extends JpaRepository<LogEvento, Long> {
}
