package org.weslleycabral.cadastro_pessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weslleycabral.cadastro_pessoas.entities.User;
import org.weslleycabral.cadastro_pessoas.entities.dto.UserDTO;
import org.weslleycabral.cadastro_pessoas.repositories.UserRepository;
import org.weslleycabral.cadastro_pessoas.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUser(Integer id) {
        return _converterForDtoUser(_getUser(id));
    }

    public List<UserDTO> getListUsers() {
        return _getListUsers().stream()
                .map(user -> {
                    var dto = new UserDTO(user.getId(), user.getName(), user.getDateOfBirth());
                    return dto;
                }).collect(Collectors.toList());
    }

    public UserDTO postUser(UserDTO userDTO) {
        return _converterForDtoUser(_createUser(userDTO));
    }

    public UserDTO putUser(UserDTO userDTO, Integer id){
        return _converterForDtoUser(_updateUser(userDTO, id));
    }

    // MÉTODOS PROTEGIDOS

    protected User _getUser(Integer userId){
        var user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Usuário de ID ").append(userId).append(" não existe");
            throw new ObjectNotFoundException(msg.toString());
        }
    }

    protected List<User> _getListUsers(){
        return userRepository.findAll();
    }

    protected User _createUser(UserDTO userDTO) {
        var user = new User(null, userDTO.name(), userDTO.dateOfBirth());
        return userRepository.save(user);
    }

    protected User _updateUser(UserDTO userDTO, Integer userId) {
        var user = _getUser(userId);
        user.setName(userDTO.name());
        user.setDateOfBirth(userDTO.dateOfBirth());
        return userRepository.save(user);
    }

    protected UserDTO _converterForDtoUser(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getDateOfBirth());
    }

    protected void _saveUserDB(User user){
        userRepository.save(user);
    }

}
