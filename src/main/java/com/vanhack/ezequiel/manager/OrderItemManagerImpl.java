package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.OrderItemDto;
import com.vanhack.ezequiel.entity.OrderDAO;
import com.vanhack.ezequiel.entity.OrderItem;
import com.vanhack.ezequiel.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class OrderItemManagerImpl implements OrderItemManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrderItems(OrderDAO orderDAO, List<OrderItemDto> orderItemDtos) {
        OrderItem orderItem;

        for(OrderItemDto orderItemDto : orderItemDtos){
            orderItem = new OrderItem();
            orderItem.setOrder(orderDAO);
            orderItem.setProduct(entityManager.find(Product.class, orderItemDto.getProduct().getId()));
            orderItem.setPrice(orderItemDto.getPrice());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setTotal(orderItemDto.getTotal());
            entityManager.persist(orderItem);
        }
    }

    @Override
    public List<OrderItemDto> retrieveOrderItemsByOrderId(Integer orderId){
        return entityManager.createNamedQuery("OrderItem.getOrderItemByOrderId", OrderItemDto.class)
                .setParameter("orderId", orderId).getResultList();
    }
}