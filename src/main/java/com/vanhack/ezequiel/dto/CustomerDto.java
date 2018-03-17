package com.vanhack.ezequiel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerDto {

    @JsonIgnore
    private Integer id;
    private String email;
    private String name;
    private String address;
    private String password;
    @JsonIgnore
    private String privateKey;


    public CustomerDto() {
    }

    public CustomerDto(Integer id, String email, String name, String address, String password, String privateKey) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.password = password;
        this.privateKey = privateKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
