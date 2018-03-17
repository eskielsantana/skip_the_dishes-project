package com.vanhack.ezequiel.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_items")
@NamedQueries({
        @NamedQuery(name="OrderItem.getOrderItemByOrderId", query="SELECT new com.vanhack.ezequiel.dto.OrderItemDto" +
                "(oi.id, oi.order.id, oi.product, oi.price, oi.quantity, oi.total) FROM OrderItem oi WHERE oi.order.id = :orderId"),
})
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDAO order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="price")
    private Double price;

    @Column(name="quantity")
    private int quantity;

    @Column(name="total")
    private Double total;

    public OrderItem() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderDAO getOrder() {
        return order;
    }

    public void setOrder(OrderDAO order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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
