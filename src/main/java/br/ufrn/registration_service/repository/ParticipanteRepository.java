package br.ufrn.registration_service.repository;

import br.ufrn.registration_service.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    List<Participante> findAllByEventoId(Long id);
}
