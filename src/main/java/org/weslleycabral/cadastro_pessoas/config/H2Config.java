package org.weslleycabral.cadastro_pessoas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.weslleycabral.cadastro_pessoas.entities.Pessoa;
import org.weslleycabral.cadastro_pessoas.repositories.PessoaRepository;

import java.util.Arrays;

@Configuration
public class H2Config implements CommandLineRunner {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public void run(String... args) throws Exception {
        var p1 = new Pessoa(null, "Lyord Souza", "23/12/1982");
        var p2 = new Pessoa(null, "Gabriel Sampaio", "13/11/1999");

        pessoaRepository.saveAll(Arrays.asList(p1,p2));
    }
}
