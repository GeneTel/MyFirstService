package org.gene.myfirstservice.service;

//import org.gene.myfirstservice.converter.OrderConverter;
//import org.gene.myfirstservice.dto.OrderDto;
//import org.gene.myfirstservice.dto.ProductDto;
//import org.gene.myfirstservice.entity.OrderEntity;
//import org.gene.myfirstservice.entity.ProductEntity;
//import org.gene.myfirstservice.repository.OrderRepository;
//import org.gene.myfirstservice.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private OrderConverter orderConverter;
//
//    public OrderDto getOrderById(Long id) {
//        return orderConverter.entityToDto(orderRepository.getOrderById(id));
//    }
//
//    public OrderDto createOrder(OrderDto orderDto) {
//        return orderConverter.entityToDto(orderRepository.createOrder(orderConverter.dtoToEntity(orderDto)));
//    }
//
//    public OrderDto updateOrder(Long id, OrderDto orderDto) {
//        return orderConverter.entityToDto(orderRepository.updateOrder(id, orderConverter.dtoToEntity(orderDto)));
//    }
//
//    public OrderDto addProductToOrder(Long orderId, ProductDto productDto) {
//        OrderEntity orderEntity = orderRepository.getOrderById(orderId);
//        ProductEntity productEntity = productRepository.getProductById(productDto.getId());
//        if (orderEntity != null && productEntity != null) {
//            orderEntity.getItems().add(productEntity);
//            orderRepository.updateOrder(orderId, orderEntity);
//        }
//        return orderConverter.entityToDto(orderEntity);
//    }
//
//    public void removeProductFromOrder(Long orderId, Long productId) {
//        OrderEntity orderEntity = orderRepository.getOrderById(orderId);
//        if (orderEntity != null) {
//            orderEntity.getItems().removeIf(item -> item.getId().equals(productId));
//            orderRepository.updateOrder(orderId, orderEntity);
//        }
//    }
//
//    public void deleteOrder(Long id) {
//        orderRepository.deleteOrder(id);
//    }
//}

import org.gene.myfirstservice.converter.OrderConverter;
import org.gene.myfirstservice.dto.OrderDto;
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
}
