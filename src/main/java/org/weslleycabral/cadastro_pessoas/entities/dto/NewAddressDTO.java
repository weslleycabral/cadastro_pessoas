package org.weslleycabral.cadastro_pessoas.entities.dto;

public record NewAddressDTO(
        String street,
        String cep,
        String number,
        Integer city,
        Boolean isPrincipal
) {
}
