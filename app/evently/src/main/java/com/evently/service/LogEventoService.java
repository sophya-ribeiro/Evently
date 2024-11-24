package com.evently.service;


import org.springframework.stereotype.Service;

import com.evently.repository.LogEventoRepository;
import com.evently.repository.entity.LogEvento;

import java.time.LocalDateTime;

@Service
public class LogEventoService {

    private final LogEventoRepository logEventoRepository;

    public LogEventoService(LogEventoRepository logEventoRepository) {
        this.logEventoRepository = logEventoRepository;
    }

    public void registrarLog(Long eventoId, String acao, String usuario, String motivo) {
        LogEvento log = new LogEvento();
        log.setEventoId(eventoId);
        log.setAcao(acao);
        log.setDataHora(LocalDateTime.now());
        log.setUsuario(usuario);
        log.setMotivo(motivo);

        logEventoRepository.save(log);
    }
}
