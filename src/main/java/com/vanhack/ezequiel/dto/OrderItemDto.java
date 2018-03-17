package com.vanhack.ezequiel.dto;

import com.vanhack.ezequiel.entity.Product;

import java.io.Serializable;

public class OrderItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int orderId;
    private ProductDto product;
    private Double price;
    private int quantity;
    private Double total;

    public OrderItemDto() {
    }

    public OrderItemDto(int orderId, ProductDto product, Double price, int quantity, Double total) {
        this.orderId = orderId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }
    public OrderItemDto(int id, int orderId, Product product, Double price, int quantity, Double total) {
        this.id = id;
        this.orderId = orderId;
        this.product = new ProductDto(product.getId(), product.getStoreId(), product.getName(),
                product.getDescription(), product.getPrice());
        this.price = price;
        this.quantity = quantity;
        this.total = total;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
