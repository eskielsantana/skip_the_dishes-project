package com.vanhack.ezequiel.dto.restDto;

import com.vanhack.ezequiel.dto.OrderDto;

import java.io.Serializable;
import java.util.List;

public class OrderRestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int customerId;
    private String deliveryAddress;
    private String contact;
    private int storeId;
    private List<OrderItemRestDto> products;

    public OrderRestDto(){ }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<OrderItemRestDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItemRestDto> products) {
        this.products = products;
    }

    public OrderDto asOrderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId(this.getCustomerId());
        orderDto.setDeliveryAddress(this.getDeliveryAddress());
        orderDto.setContact(this.getContact());
        orderDto.setStoreId(this.getStoreId());
        return orderDto;
    }

    @Override
    public String toString() {
        return "OrderRestDto{" +
                "customerId=" + customerId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contact='" + contact + '\'' +
                ", storeId=" + storeId +
                ", products=" + products +
                '}';
    }
}
