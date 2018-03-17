package com.vanhack.ezequiel.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="orders")
@NamedQueries({
        @NamedQuery(name="OrderDAO.getOrderById", query="SELECT new com.vanhack.ezequiel.dto.OrderDto(o.id, o.date, o.customerId, " +
                "o.deliveryAddress, o.contact, o.storeId, o.total, o.status.name, o.lastUpdate) FROM OrderDAO o WHERE o.id = :orderId AND o.isActive = true"),
})
public class OrderDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="date")
    private Date date;

    @Column(name="customer_id")
    private int customerId;

    @Column(name="delivery_address")
    private String deliveryAddress;

    @Column(name="contact")
    private String contact;

    @Column(name="store_id")
    private int storeId;

    @Column(name="total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name="last_update")
    private Date lastUpdate;

    @Column(name="is_active")
    private Boolean isActive;

    public OrderDAO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
