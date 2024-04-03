package org.weslleycabral.cadastro_pessoas.entities.dto;

public record AddressDTO(
        Integer id,
        String street,
        String number,
        String city,
        String cep,
        Boolean principalAddress
) {
}
