package org.weslleycabral.cadastro_pessoas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.weslleycabral.cadastro_pessoas.entities.Address;
import org.weslleycabral.cadastro_pessoas.entities.City;
import org.weslleycabral.cadastro_pessoas.entities.State;
import org.weslleycabral.cadastro_pessoas.entities.User;
import org.weslleycabral.cadastro_pessoas.repositories.AddressRepository;
import org.weslleycabral.cadastro_pessoas.repositories.CityRepository;
import org.weslleycabral.cadastro_pessoas.repositories.StateRepository;
import org.weslleycabral.cadastro_pessoas.repositories.UserRepository;

import java.util.Arrays;

@Configuration
public class H2Config implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Override
    public void run(String... args) throws Exception {
        var user01 = new User(null, "Ana Maria", "23/12/1982");
        var user02 = new User(null, "Gabriel Sampaio", "13/11/1999");
        var user03 = new User(null, "Lucas Moura", "10/10/1960");

        var state01 = new State(null, "São Paulo");
        var state02 = new State(null, "Ceará");
        var state03 = new State(null, "Paraná");

        var city01 = new City(null, "São Paulo", state01);
        var city02 = new City(null, "Juazeiro do Norte", state02);
        var city03 = new City(null, "Fortaleza", state02);
        var city04 = new City(null, "Curitiba", state03);
        var city05 = new City(null, "Paranaguá", state03);
        var city06 = new City(null, "Santos", state01);

        state01.getCities().addAll(Arrays.asList(city01, city06));
        state02.getCities().addAll(Arrays.asList(city02, city03));
        state03.getCities().addAll(Arrays.asList(city04, city05));

        stateRepository.saveAll(Arrays.asList(state01, state02, state03));
        cityRepository.saveAll(Arrays.asList(city01, city02, city03, city04, city05, city06));

        var address02 = new Address(null, "Praça Samuel Walquer Almeida", "02002-000", "22", city03, true);
        var address03 = new Address(null, "Avenida 9 de Julho", "03003-000", "33", city01, true);
        var address04 = new Address(null, "Caminho Particular São Jorge", "04004-000", "44", city06);
        var address05 = new Address(null, "Dalila Rolim Vargas", "05005-000", "55", city04);
        var address06 = new Address(null, "Praça Jorge Luiz Ultrabo Pinto", "06006-000", "66", city05, true);

        addressRepository.saveAll(Arrays.asList(address02, address03, address04, address05, address06));

        user01.getAddresses().add(address02);
        user02.getAddresses().addAll(Arrays.asList(address03, address05));
        user03.getAddresses().addAll(Arrays.asList(address05, address06));

        userRepository.saveAll(Arrays.asList(user01, user02, user03));

        user01.getAddresses().get(0).setPrincipal(true);
        user02.getAddresses().get(0).setPrincipal(true);
        user03.getAddresses().get(0).setPrincipal(true);

        userRepository.saveAll(Arrays.asList(user01, user02, user03));

    }
}
