package org.weslleycabral.cadastro_pessoas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weslleycabral.cadastro_pessoas.entities.dto.UserDTO;
import org.weslleycabral.cadastro_pessoas.services.UserService;
import org.weslleycabral.cadastro_pessoas.utils.SuccessResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        var user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAll() {
        var userList = service.findAll();
        return ResponseEntity.ok().body(userList);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> save(@RequestBody UserDTO userDTO) {
        var newUser = service.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new SuccessResponse(
                    "Novo usuário cadastrado com sucesso!",
                    newUser,
                    LocalDateTime.now()
            )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        var userUpdate = service.update(userDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new SuccessResponse(
                        "Usuário atualizado com sucesso!",
                        userUpdate,
                        LocalDateTime.now()
                )
        );
    }
}
