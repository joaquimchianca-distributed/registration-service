package br.ufrn.registration_service.service;

import br.ufrn.registration_service.client.EventoCliente;
import br.ufrn.registration_service.model.Participante;
import br.ufrn.registration_service.model.dto.Evento;
import br.ufrn.registration_service.model.dto.ParticipanteRequest;
import br.ufrn.registration_service.model.dto.ParticipanteResponse;
import br.ufrn.registration_service.repository.ParticipanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {
    private final ParticipanteRepository participanteRepository;
    private final EventoCliente eventoCliente;

    public ParticipanteService(ParticipanteRepository participanteRepository, EventoCliente eventoCliente) {
        this.participanteRepository = participanteRepository;
        this.eventoCliente = eventoCliente;
    }

    public ParticipanteResponse inscreveParticipante(ParticipanteRequest participanteRequest,
                                                     Long eventoId) {
        Evento evento = eventoCliente.buscaEventoPorId(eventoId);
        if (evento == null) {
            throw new EntityNotFoundException("O evento não existe");
        }

        if (evento.qtdParticipantes() >= evento.maxParticipantes()) {
            throw new RuntimeException("O evento já possui quantidade máxima de participantes");
        }

        Participante participante = Participante.builder()
                .nomeParticipante(participanteRequest.nome())
                .email(participanteRequest.email())
                .eventoId(evento.id())
                .build();
        Participante participanteSalvo = participanteRepository.save(participante);
        eventoCliente.adicionaParticipanteEvento(evento.id());
        return new ParticipanteResponse(participanteSalvo);
    }

    public List<ParticipanteResponse> listarParticipantes() {
        List<Participante> participantes = participanteRepository.findAll();
        List<ParticipanteResponse> resposta =
                participantes.stream().map(p -> new ParticipanteResponse(p)).toList();
        return resposta;
    }


    public List<ParticipanteResponse> listarParticipantesEvento(Long eventoId) {
        List<Participante> participantesEvento = participanteRepository.findAllByEventoId(eventoId);
        List<ParticipanteResponse> resposta = participantesEvento.stream().map(
                p -> new ParticipanteResponse(p)).toList();
        return resposta;
    }

    public void removeParticipante(Long id, Long eventoId) {
        Evento evento = eventoCliente.buscaEventoPorId(eventoId);
        if (evento == null) {
            throw new RuntimeException("O evento não existe");
        }
        eventoCliente.removeParticipanteEvento(evento.id());
        participanteRepository.deleteById(id);
    }
}
