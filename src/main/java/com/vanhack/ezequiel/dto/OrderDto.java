package com.vanhack.ezequiel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDto implements Serializable {

    public static final String DATA_PATTERN = "yyyy-MM-dd HH:mm:ssZ";

    private static final long serialVersionUID = 1L;
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_PATTERN)
    private Date date;
    private int customerId;
    private String deliveryAddress;
    private String contact;
    private int storeId;
    private List<OrderItemDto> orderItems;
    private Double total;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATA_PATTERN)
    private Date lastUpdate;

    public OrderDto(){ }

    public OrderDto(int id, Date date, int customerId, String deliveryAddress, String contact, int storeId, Double total, String status, Date lastUpdate) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.contact = contact;
        this.storeId = storeId;
        this.total = total;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", date=" + date +
                ", customerId=" + customerId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contact='" + contact + '\'' +
                ", storeId=" + storeId +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
