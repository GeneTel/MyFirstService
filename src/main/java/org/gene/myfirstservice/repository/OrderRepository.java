package org.gene.myfirstservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.gene.myfirstservice.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Map<Long, OrderEntity> orders = new HashMap<>();

    public default OrderEntity getOrderById(Long id) {
        return orders.get(id);
    }

    public default OrderEntity createOrder(OrderEntity orderEntity) {
        orders.put(orderEntity.getId(), orderEntity);
        return orderEntity;
    }

    public default OrderEntity updateOrder(Long id, OrderEntity orderEntity) {
        orders.put(id, orderEntity);
        return orderEntity;
    }

    public default void deleteOrder(Long id) {
        orders.remove(id);
    }
}
