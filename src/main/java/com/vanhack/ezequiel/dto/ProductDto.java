package com.vanhack.ezequiel.dto;

public class ProductDto {

    private int id;
    private int storeId;
    private String name;
    private String description;
    private Double price;

    public ProductDto() {
    }

    public ProductDto(int id, int storeId, String name, String description, Double price) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDto(int id, int storeId, String name, Double price) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
