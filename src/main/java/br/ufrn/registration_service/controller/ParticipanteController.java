package br.ufrn.registration_service.controller;

import br.ufrn.registration_service.model.dto.ParticipanteRequest;
import br.ufrn.registration_service.model.dto.ParticipanteResponse;
import br.ufrn.registration_service.service.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class ParticipanteController {
    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @PostMapping("/{eventoId}")
    public ResponseEntity<ParticipanteResponse> inscreveParticipante(
            @RequestBody @Valid ParticipanteRequest participanteRequest,
            @PathVariable Long eventoId) {
        return new ResponseEntity<>(participanteService.inscreveParticipante(participanteRequest,
                                                                             eventoId)
                , HttpStatus.CREATED);
    }

    @GetMapping("/{eventoId}")
    public ResponseEntity<List<ParticipanteResponse>> listaParticipantesEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(participanteService.listarParticipantesEvento(eventoId));
    }

    @DeleteMapping("/{eventoId}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeParticipanteEvento(@PathVariable Long id, @PathVariable Long eventoId) {
        participanteService.removeParticipante(id, eventoId);
    }
}
