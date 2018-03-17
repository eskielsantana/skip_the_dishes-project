package com.vanhack.ezequiel.dto.restDto;

import java.io.Serializable;
import java.util.List;

public class OrderListRestDto implements Serializable {

    private List<OrderRestDto> orderRestDtos;

    public OrderListRestDto() {
    }

    public OrderListRestDto(List<OrderRestDto> orderRestDtos) {
        this.orderRestDtos = orderRestDtos;
    }

    public List<OrderRestDto> getOrderRestDtos() {
        return orderRestDtos;
    }
}
