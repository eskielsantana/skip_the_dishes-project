package com.vanhack.ezequiel.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="products")
@NamedQueries({
        @NamedQuery(name="Product.retrieveProductById", query="SELECT new com.vanhack.ezequiel.dto.ProductDto(p.id, p.storeId, p.name, p.price) FROM Product p WHERE p.id = :productId"),
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="store_id")
    private int storeId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    public Product() {}

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
