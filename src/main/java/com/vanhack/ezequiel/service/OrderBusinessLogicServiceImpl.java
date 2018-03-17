package com.vanhack.ezequiel.service;

import com.vanhack.ezequiel.dto.OrderDto;
import com.vanhack.ezequiel.dto.OrderItemDto;
import com.vanhack.ezequiel.dto.ProductDto;
import com.vanhack.ezequiel.dto.restDto.OrderItemRestDto;
import com.vanhack.ezequiel.dto.restDto.OrderRestDto;
import com.vanhack.ezequiel.entity.OrderDAO;
import com.vanhack.ezequiel.manager.OrderItemManager;
import com.vanhack.ezequiel.manager.OrderManager;
import com.vanhack.ezequiel.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBusinessLogicServiceImpl implements OrderBusinessLogicService {

    @Autowired
    private OrderManager orderManager;

    @Autowired
    private ProductManager productManager;

    @Autowired
    private OrderItemManager orderItemManager;

    @Override
    public OrderDto getOrderById(int orderId) {
        OrderDto orderDto = orderManager.getOrderById(orderId);
        if(orderDto != null){
            orderDto.setOrderItems(orderItemManager.retrieveOrderItemsByOrderId(orderId));
            return orderDto;
        }
        return null;
    }

    @Override
    public Integer requestOrder(OrderRestDto orderRestDto) throws Exception {

        OrderDto orderDto = orderRestDto.asOrderDto();
        OrderItemVO itemVO = retrieveOrderProducts(orderDto.getId(), orderRestDto.getProducts());
        orderDto.setTotal(itemVO.getTotalPrice());
        OrderDAO dao = orderManager.requestOrder(orderDto);
        orderItemManager.saveOrderItems(dao, itemVO.getOrderItemDtos());
        return dao.getId();
    }

    @Override
    public void requestOrders(List<OrderRestDto> ordersRestDto) throws Exception {

        OrderDto orderDto;
        OrderItemVO orderItemVO;
        OrderDAO orderDAO;

        for(OrderRestDto orderRestDto : ordersRestDto){
            orderDto = orderRestDto.asOrderDto();
            orderItemVO = retrieveOrderProducts(orderDto.getId(), orderRestDto.getProducts());
            orderDto.setTotal(orderItemVO.getTotalPrice());
            orderDAO = orderManager.requestOrder(orderDto);
            orderItemManager.saveOrderItems(orderDAO, orderItemVO.getOrderItemDtos());
        }

        ordersRestDto.stream().forEach((orderRestDto) -> {

        });
    }

    @Override
    public void cancelOrderById(Integer orderId) throws Exception {
        orderManager.cancelOrderById(orderId);
    }

    @Override
    public String getOrderStatusById(Integer orderId){
        return orderManager.getOrderStatusById(orderId);
    }

    private OrderItemVO retrieveOrderProducts(Integer orderId, List<OrderItemRestDto> orderItems) throws Exception {

        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        ProductDto productDto;
        Double productPrice;
        Double totalPrice = 0.0;

        for(OrderItemRestDto orderItemRestDto : orderItems){
            productDto = productManager.retrieveProductById(orderItemRestDto.getProductId());

            if(productDto != null){
                productPrice = productDto.getPrice() * orderItemRestDto.getQuantity();
                orderItemDtos.add(new OrderItemDto(orderId, productDto, productDto.getPrice(), orderItemRestDto.getQuantity(), productPrice));
                totalPrice += productPrice;
            }else{
                throw new Exception("Product with id (" + orderItemRestDto.getProductId()+ ") not found.");
            }
        }
        return new OrderItemVO(orderItemDtos, totalPrice);
    }

    private final class OrderItemVO {
        private List<OrderItemDto> orderItemDtos;
        private Double totalPrice;

        public OrderItemVO(List<OrderItemDto> orderItemDtos, Double totalPrice) {
            this.orderItemDtos = orderItemDtos;
            this.totalPrice = totalPrice;
        }

        public List<OrderItemDto> getOrderItemDtos() {
            return orderItemDtos;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }
    }

}