package org.weslleycabral.cadastro_pessoas.entities.dto;

import org.weslleycabral.cadastro_pessoas.entities.Address;

import java.util.List;

public record UserDTO(Integer id, String name, String dateOfBirth, List<Address> addresses) {
}
