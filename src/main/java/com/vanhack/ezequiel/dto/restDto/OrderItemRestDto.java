package com.vanhack.ezequiel.dto.restDto;

import java.io.Serializable;

public class OrderItemRestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;
    private Integer quantity;

    public OrderItemRestDto() {  }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
