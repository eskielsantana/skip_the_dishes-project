package com.vanhack.ezequiel.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="customer_token")
@NamedQueries({
        @NamedQuery(name="CustomerToken.checkIfTokenIsValid", query="SELECT ct.id FROM CustomerToken ct WHERE ct.token = :token AND ct.validityDate > CURRENT_TIME AND ct.isActive = true"),
        @NamedQuery(name="CustomerToken.invalidateOldCustomerToken", query="UPDATE CustomerToken ct SET ct.isActive = false WHERE ct.customer.id = :customerId"),
})

public class CustomerToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name="token")
    private String token;

    @Column(name="validity_date")
    private Date validityDate;

    @Column(name="is_active")
    private Boolean isActive;

    public CustomerToken() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
