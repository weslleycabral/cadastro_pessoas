package org.weslleycabral.cadastro_pessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.weslleycabral.cadastro_pessoas.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
