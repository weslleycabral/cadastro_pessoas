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
        var user01 = new User(null, "Lyord Souza", "23/12/1982");
        var user02 = new User(null, "Gabriel Sampaio", "13/11/1999");

        var state01 = new State(null, "São Paulo");
        var state02 = new State(null, "Ceará");

        var city01 = new City(null, "São Paulo", state01);
        var city02 = new City(null, "Juazeiro do Norte", state02);
        var city03 = new City(null, "Fortaleza", state02);

        state01.getCities().add(city01);
        state02.getCities().addAll(Arrays.asList(city02, city03));

        stateRepository.saveAll(Arrays.asList(state01, state02));
        cityRepository.saveAll(Arrays.asList(city01, city02, city03));

        var address01 = new Address(null, "Rua Superman", "01582123", "07", city01);
        var address02 = new Address(null, "Rua Super Metroid", "63025148", "99", city02);

        addressRepository.saveAll(Arrays.asList(address01,address02));

        user01.getAddresses().add(address01);
        user01.getAddresses().add(address02);

        user02.getAddresses().add(address02);

        userRepository.saveAll(Arrays.asList(user01,user02));
        addressRepository.saveAll(Arrays.asList(address01,address02));

    }
}
