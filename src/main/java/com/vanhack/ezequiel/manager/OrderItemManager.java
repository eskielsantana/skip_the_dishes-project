package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.OrderItemDto;
import com.vanhack.ezequiel.entity.OrderDAO;

import java.util.List;

public interface OrderItemManager {

    void saveOrderItems(OrderDAO orderDAO, List<OrderItemDto> orderItemDtos);

    List<OrderItemDto> retrieveOrderItemsByOrderId(Integer orderId);

}