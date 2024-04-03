package org.weslleycabral.cadastro_pessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weslleycabral.cadastro_pessoas.entities.City;
import org.weslleycabral.cadastro_pessoas.repositories.CityRepository;
import org.weslleycabral.cadastro_pessoas.services.exceptions.ObjectNotFoundException;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public City findById(Integer id) {
        var city = repository.findById(id);
        return city.orElseThrow(
                () -> {
                    StringBuilder msg = new StringBuilder();
                    msg.append("A cidade com ID ").append(id).append(" não existe no banco de dados!");
                    throw new ObjectNotFoundException(msg.toString());
                }
        );
    }
}
