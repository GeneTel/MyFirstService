package org.gene.myfirstservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.gene.myfirstservice.entity.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    private Map<Long, OrderEntity> orders = new HashMap<>();

    public OrderEntity getOrderById(Long id) {
        return orders.get(id);
    }

    public OrderEntity createOrder(OrderEntity orderEntity) {
        orders.put(orderEntity.getId(), orderEntity);
        return orderEntity;
    }

    public OrderEntity updateOrder(Long id, OrderEntity orderEntity) {
        orders.put(id, orderEntity);
        return orderEntity;
    }

    public void deleteOrder(Long id) {
        orders.remove(id);
    }
}