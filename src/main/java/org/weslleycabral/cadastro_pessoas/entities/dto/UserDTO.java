package org.weslleycabral.cadastro_pessoas.entities.dto;

import jakarta.validation.constraints.NotBlank;
import org.weslleycabral.cadastro_pessoas.entities.Address;

import java.util.List;

public record UserDTO(Integer id, @NotBlank String name, @NotBlank String dateOfBirth, List<Address> addresses) {
}
