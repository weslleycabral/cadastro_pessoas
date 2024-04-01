package org.weslleycabral.cadastro_pessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.weslleycabral.cadastro_pessoas.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
