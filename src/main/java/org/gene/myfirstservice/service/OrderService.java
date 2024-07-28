package org.gene.myfirstservice.service;


import org.gene.myfirstservice.converter.OrderConverter;
import org.gene.myfirstservice.dto.OrderDto;
import org.gene.myfirstservice.dto.ProductDto;
import org.gene.myfirstservice.entity.OrderEntity;
import org.gene.myfirstservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    public OrderDto getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        return orderConverter.entityToDto(orderEntity);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = orderConverter.dtoToEntity(orderDto);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        return orderConverter.entityToDto(savedOrder);
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        if (orderEntity != null) {
            orderEntity.setCustomerName(orderDto.getCustomerName());
            orderEntity.setTotalPrice(orderDto.getTotalPrice());
            orderEntity.setItems(orderConverter.dtoToEntity(orderDto).getItems());
            OrderEntity updatedOrder = orderRepository.save(orderEntity);
            return orderConverter.entityToDto(updatedOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto addProductToOrder(Long orderId, ProductDto productDto) {
        return null;
    }

    public void removeProductFromOrder(Long orderId, Long productId) {
    }
}
