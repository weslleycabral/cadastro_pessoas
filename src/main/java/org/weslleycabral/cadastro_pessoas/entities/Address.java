package org.weslleycabral.cadastro_pessoas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_ADDRESS")
public class Address implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String cep;
    private String number;
    private Boolean isPrincipal;
    @JsonIgnore
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    public Address() {}

    public Address(Integer id, String street, String cep, String number, City city) {
        this.id = id;
        this.street = street;
        this.cep = cep;
        this.number = number;
        this.city = city;
        this.isPrincipal = false;
    }

    public Address(Integer id, String street, String cep, String number, City city, Boolean isPrincipal) {
        this.id = id;
        this.street = street;
        this.cep = cep;
        this.number = number;
        this.city = city;
        this.isPrincipal = isPrincipal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<User> getUsers() {
        return users;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Boolean getPrincipal() {
        return isPrincipal;
    }

    public void setPrincipal(Boolean principal) {
        isPrincipal = principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(cep, address.cep) && Objects.equals(number, address.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, number);
    }
}
