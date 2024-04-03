package org.weslleycabral.cadastro_pessoas.resources;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weslleycabral.cadastro_pessoas.entities.dto.AddressDTO;
import org.weslleycabral.cadastro_pessoas.entities.dto.NewAddressDTO;
import org.weslleycabral.cadastro_pessoas.entities.dto.UserDTO;
import org.weslleycabral.cadastro_pessoas.services.AddressService;
import org.weslleycabral.cadastro_pessoas.services.SuccessResponseService;
import org.weslleycabral.cadastro_pessoas.services.UserService;
import org.weslleycabral.cadastro_pessoas.utils.SuccessResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private SuccessResponseService successService;

    // USER
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsersList() {
        var list = userService.getListUsers();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        var user = userService.getUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> postUser(@Valid @RequestBody UserDTO userDTO) {
        var user = userService.postUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                successService.successResponse("Novo usuário cadastrado com sucesso!", user, LocalDateTime.now())
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<SuccessResponse> putUser(@PathVariable Integer userId, @Valid @RequestBody UserDTO userDTO) {
        var user = userService.putUser(userDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                successService.successResponse("Usuário atualizado!", user, LocalDateTime.now()));
    }

    //ADDRESS
    @GetMapping("/{userId}/address")
    public ResponseEntity<List<AddressDTO>> getAddressListByUserId(@PathVariable Integer userId) {
        var allAddress = addressService.getListAddress(userId);
        return ResponseEntity.ok().body(allAddress);
    }

    @GetMapping("/{userId}/address/{addressId}")
    public ResponseEntity<AddressDTO> getAddressByUserId(@PathVariable Integer userId, @PathVariable Integer addressId) {
        var allAddress = addressService.getAddress(userId, addressId);
        return ResponseEntity.ok().body(allAddress);
    }
    @PostMapping("/{userId}/address")
    public ResponseEntity<SuccessResponse> postAddress(@PathVariable Integer userId, @RequestBody NewAddressDTO newAddressDTO) {
        var address = addressService.postAddress(userId, newAddressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                successService.successResponse("Endereço criado com sucesso!", address, LocalDateTime.now()));
    }

    @PutMapping("/{userId}/address/{addressId}")
    public ResponseEntity<SuccessResponse> putAddress(@PathVariable Integer userId, @PathVariable Integer addressId, @RequestBody NewAddressDTO newAddressDTO) {
        var address = addressService.putAddress(userId, addressId, newAddressDTO);
        return ResponseEntity.ok().body(
                successService.successResponse("Endereço atualizado!", address, LocalDateTime.now())
        );
    }

}
