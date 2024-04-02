package org.weslleycabral.cadastro_pessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.weslleycabral.cadastro_pessoas.entities.Address;
import org.weslleycabral.cadastro_pessoas.entities.City;

public interface CityRepository extends JpaRepository<City, Integer> {
}
