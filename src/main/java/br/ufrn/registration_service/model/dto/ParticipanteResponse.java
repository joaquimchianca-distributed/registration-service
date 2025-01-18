package br.ufrn.registration_service.model.dto;

import br.ufrn.registration_service.model.Participante;

public record ParticipanteResponse(
        Long id,
        String nome,
        String email,
        Long eventoId
) {
    public ParticipanteResponse(Participante participante) {
        this(participante.getId(), participante.getNomeParticipante(), participante.getEmail(),
             participante.getEventoId());
    }
}
