package com.vanhack.ezequiel.service;

import com.vanhack.ezequiel.dto.OrderDto;
import com.vanhack.ezequiel.dto.restDto.OrderRestDto;
import java.util.List;

public interface OrderBusinessLogicService {

    OrderDto getOrderById(int orderId);
    Integer requestOrder(OrderRestDto orderRestDto) throws Exception;
    void requestOrders(List<OrderRestDto> ordersRestDto) throws Exception;
    void cancelOrderById(Integer orderId) throws Exception;
    String getOrderStatusById(Integer orderId);
}