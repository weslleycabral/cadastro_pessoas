package org.weslleycabral.cadastro_pessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weslleycabral.cadastro_pessoas.entities.Address;
import org.weslleycabral.cadastro_pessoas.entities.dto.AddressDTO;
import org.weslleycabral.cadastro_pessoas.entities.dto.NewAddressDTO;
import org.weslleycabral.cadastro_pessoas.repositories.AddressRepository;
import org.weslleycabral.cadastro_pessoas.services.exceptions.EmptyListException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    // MÉTODOS PÚBLICOS

    public List<AddressDTO> getListAddress(Integer userId) {
        return _getListAddress(userId).stream()
                .map(address -> _converterForDtoAddress(address))
                .collect(Collectors.toList());
    }

    public AddressDTO getAddress(Integer userId, Integer addressId) {
        return _converterForDtoAddress(_getAddress(userId, addressId));
    }

    public AddressDTO postAddress(Integer userId, NewAddressDTO newAddressDTO) {
        return _converterForDtoAddress(_createAddress(userId, newAddressDTO));
    }

    public AddressDTO putAddress(Integer userId, Integer addressId, NewAddressDTO newAddressDTO) {
        return _converterForDtoAddress(_updateAddress(userId, addressId, newAddressDTO));
    }

    // MÉTODOS PROTEGIDOS

    protected Address _getAddress(Integer userId, Integer addressId){
        var notPresente = true;
        for (Address a : _getListAddress(userId)) {
            if (a.getId() == addressId) {
                notPresente = false;
                return a;
            } else {
                notPresente = true;
            }
        }
        if (notPresente) {
            StringBuilder msg = new StringBuilder();
            msg.append(userService._getUser(userId).getName())
                    .append(" não possui endereço com ID ")
                    .append(addressId);
            throw new EmptyListException(msg.toString());
        }
        return null;
    }

    protected List<Address> _getListAddress(Integer userId) {
        var user = userService._getUser(userId);
        if (!user.getAddresses().isEmpty()) {
            return user.getAddresses();
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("O usuário ").append(user.getName()).append(" não possui endereços cadastrados!");
            throw new EmptyListException(msg.toString());
        }
    }

    protected Address _createAddress(Integer userId, NewAddressDTO newAddressDTO){
        var addressList = _getListAddress(userId);
        var newAddress = new Address(
                null,
                newAddressDTO.street(),
                newAddressDTO.cep(),
                newAddressDTO.number(),
                cityService.findById(userId)
        );
        addressList.add(newAddress);
        _saveAddressDB(newAddress);
        userService._saveUserDB(userService._getUser(userId));
        _setPrincipalOrNo(userId, newAddress.getId(), newAddressDTO.isPrincipal());
        return newAddress;
    }

    protected Address _updateAddress(Integer userId, Integer addressId, NewAddressDTO newAddressDTO){
        var address = _getAddress(userId, addressId);

        if(newAddressDTO.street() != null && newAddressDTO.street() != ""){
            address.setStreet(newAddressDTO.street());
        }
        if(newAddressDTO.cep() != null && newAddressDTO.cep() != ""){
            address.setCep(newAddressDTO.cep());
        }
        if (newAddressDTO.number() != null && newAddressDTO.number() != ""){
            address.setNumber(newAddressDTO.number());
        }
        if(newAddressDTO.isPrincipal() != null) {
            address.setPrincipal(newAddressDTO.isPrincipal());
        }
        _saveAddressDB(address);
        _setPrincipalOrNo(userId, addressId, address.getPrincipal());
        return address;
    }

    protected void _setPrincipalOrNo(Integer userId, Integer addressId, Boolean bool){
        var addressList = _getListAddress(userId);
        if (addressList.size() == 1) {
            addressList.get(0).setPrincipal(true);
            _saveAddressDB(addressList.get(0));
        } else {
            if(bool) {
                for (Address a : addressList){
                    if (a.getId() == addressId) {
                        a.setPrincipal(true);
                    } else {
                        a.setPrincipal(false);
                    }
                    _saveAddressDB(a);
                }
            }
        }
    }

    protected AddressDTO _converterForDtoAddress(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getCity().getName(),
                address.getCep(),
                address.getPrincipal()
        );
    }

    protected void _saveAddressDB(Address address){
        addressRepository.save(address);
    }

}

