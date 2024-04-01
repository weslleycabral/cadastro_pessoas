package org.weslleycabral.cadastro_pessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.weslleycabral.cadastro_pessoas.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
