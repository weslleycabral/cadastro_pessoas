package org.weslleycabral.cadastro_pessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weslleycabral.cadastro_pessoas.entities.Address;
import org.weslleycabral.cadastro_pessoas.entities.User;
import org.weslleycabral.cadastro_pessoas.entities.dto.AddressDTO;
import org.weslleycabral.cadastro_pessoas.entities.dto.UserDTO;
import org.weslleycabral.cadastro_pessoas.repositories.UserRepository;
import org.weslleycabral.cadastro_pessoas.services.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO findById(Integer id) {
        var user = repository.findById(id);
        if (user.isPresent()) {
            var userDTO = new UserDTO(user.get().getId(), user.get().getName(), user.get().getDateOfBirth(), user.get().getAddresses());
            return userDTO;
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Usuário de ID ").append(id).append(" não existe");
            throw new ObjectNotFoundException(msg.toString());
        }
    }

    public List<AddressDTO> findAddressById(Integer id) {
        var user = repository.findById(id);
        if (user.isPresent()){
            if (!user.get().getAddresses().isEmpty()) {
                var addressListDTO = new ArrayList<AddressDTO>();

                for(Address a : user.get().getAddresses()) {
                    var addressDTO = new AddressDTO(
                            a.getStreet(),
                            a.getNumber(),
                            a.getCity().getName(),
                            a.getCep());
                    addressListDTO.add(addressDTO);
                }

                return addressListDTO;
            } else {
                StringBuilder msg = new StringBuilder();
                msg.append("O usuário ").append(user.get().getName()).append(" não possui endereços cadastrados!");
                throw new ObjectNotFoundException(msg.toString());
            }
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Usuário de ID ").append(id).append(" não existe");
            throw new ObjectNotFoundException(msg.toString());
        }
    }

    public List<UserDTO> findAll() {
        var userList = repository.findAll();
        return userList.stream()
                .map(user -> {
                    var dto = new UserDTO(user.getId(), user.getName(), user.getDateOfBirth(), user.getAddresses());
                    return dto;
                }).collect(Collectors.toList());
    }

    public UserDTO save(UserDTO userDTO) {
        var user = new User(null, userDTO.name(), userDTO.dateOfBirth());
        user = repository.save(user);
        return new UserDTO(user.getId(),user.getName(),user.getDateOfBirth(),user.getAddresses());
    }

    public UserDTO update(UserDTO userDTO, Integer id){
        var user = repository.findById(id);

        if(user.isPresent()){
            var userUpdate = user.get();
            userUpdate.setName(userDTO.name());
            userUpdate.setDateOfBirth(userDTO.dateOfBirth());
            repository.save(userUpdate);
            return new UserDTO(id, userUpdate.getName(), userUpdate.getDateOfBirth(), userUpdate.getAddresses());
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Usuário de ID ").append(id).append(" não existe");
            throw new ObjectNotFoundException(msg.toString());
        }
    }

    public UserDTO delete(Integer id) {
        var user = repository.findById(id);

        if(user.isPresent()){
            var deleteUser = user.get();
            repository.deleteById(deleteUser.getId());
            return new UserDTO(deleteUser.getId(), deleteUser.getName(), deleteUser.getDateOfBirth(), deleteUser.getAddresses());
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Usuário de ID ").append(id).append(" não existe");
            throw new ObjectNotFoundException(msg.toString());
        }
    }
}
