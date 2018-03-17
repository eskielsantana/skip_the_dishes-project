package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.OrderDto;
import com.vanhack.ezequiel.entity.OrderDAO;
import com.vanhack.ezequiel.entity.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class OrderManagerImpl implements OrderManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrderDto getOrderById(int orderId) {

        List<OrderDto> result = entityManager.createNamedQuery("OrderDAO.getOrderById", OrderDto.class)
                .setParameter("orderId", orderId).getResultList();

        return (!result.isEmpty()) ? result.get(0) : null;
    }

    @Override
    public OrderDAO requestOrder(OrderDto dto) {
        OrderDAO dao = new OrderDAO();
        dao.setDate(new Date());
        dao.setCustomerId(dto.getCustomerId());
        dao.setDeliveryAddress(dto.getDeliveryAddress());
        dao.setContact(dto.getContact());
        dao.setStoreId(dto.getStoreId());
        dao.setTotal(dto.getTotal());
        dao.setStatus(entityManager.find(Status.class, Status.NEW));
        dao.setLastUpdate(new Date());
        dao.setIsActive(true);
        entityManager.persist(dao);
        return dao;
    }

    @Override
    public void cancelOrderById(Integer orderId) throws Exception {
        OrderDAO orderDAO = entityManager.find(OrderDAO.class, orderId);
        if(orderDAO != null){
            orderDAO.setStatus(entityManager.find(Status.class, Status.CANCELLED));
            orderDAO.setIsActive(false);
        }else{
            throw new Exception("Order not found");
        }
    }

    @Override
    public String getOrderStatusById(Integer orderId){
        OrderDAO orderDAO = entityManager.find(OrderDAO.class, orderId);
        return (orderDAO != null) ? orderDAO.getStatus().getName() : null;
    }
}