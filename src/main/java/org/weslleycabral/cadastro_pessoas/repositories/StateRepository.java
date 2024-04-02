package org.weslleycabral.cadastro_pessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.weslleycabral.cadastro_pessoas.entities.State;

public interface StateRepository extends JpaRepository<State, Integer> {
}
