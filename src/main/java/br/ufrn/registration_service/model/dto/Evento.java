package br.ufrn.registration_service.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record Evento(
        Long id,
        String nomeEvento,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataInicio,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataFim,

        Boolean podeInscrever,
        Long qtdParticipantes,
        Long maxParticipantes
) {
}
