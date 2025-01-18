package br.ufrn.registration_service.model.dto;

import jakarta.validation.constraints.*;

public record ParticipanteRequest(
        @NotBlank(message = "O nome não pode ser vazio")
        String nome,

        @NotBlank(message = "O email não pode ser vazio")
        @Email(message = "Formato inválido de email")
        String email
) {
}
