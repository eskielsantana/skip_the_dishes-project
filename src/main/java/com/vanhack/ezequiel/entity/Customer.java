package com.vanhack.ezequiel.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="customer")
@NamedQueries({
        @NamedQuery(name="Customer.retrieveCustomerByEmail", query="SELECT new com.vanhack.ezequiel.dto.CustomerDto( c.id, " +
                "c.email, c.name, c.address, c.password, c.privateKey) FROM Customer c WHERE c.email = :email AND c.isActive = true"),
        @NamedQuery(name="Customer.checkDuplicatedEmail", query="SELECT c.id FROM Customer c WHERE c.email = :email AND c.isActive = true"),
})

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="creation")
    private Date date;

    @Column(name="password")
    private String password;

    @Column(name="private_key")
    private String privateKey;

    @Column(name="is_active")
    private Boolean isActive;

    public Customer() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
