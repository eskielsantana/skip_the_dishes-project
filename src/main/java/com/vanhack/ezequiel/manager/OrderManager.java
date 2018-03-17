package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.OrderDto;
import com.vanhack.ezequiel.entity.OrderDAO;

public interface OrderManager {

    OrderDto getOrderById(int orderId);
    OrderDAO requestOrder(OrderDto event);
    void cancelOrderById(Integer orderId) throws Exception;
    String getOrderStatusById(Integer orderId);
}