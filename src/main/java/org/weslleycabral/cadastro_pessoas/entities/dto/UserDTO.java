package org.weslleycabral.cadastro_pessoas.entities.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        Integer id,
        @NotBlank String name,
        @NotBlank String dateOfBirth
) {
}
