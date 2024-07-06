package org.gene.myfirstservice.converter;

import org.gene.myfirstservice.dto.OrderDto;
import org.gene.myfirstservice.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderConverter {

    @Autowired
    private ItemConverter itemConverter;

    public OrderDto entityToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setItems(orderEntity.getItems().stream().map(itemConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }

    public OrderEntity dtoToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        orderEntity.setItems(orderDto.getItems().stream().map(itemConverter::dtoToEntity).collect(Collectors.toList()));
        return orderEntity;
    }
}