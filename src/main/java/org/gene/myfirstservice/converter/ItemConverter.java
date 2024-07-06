package org.gene.myfirstservice.converter;

import org.gene.myfirstservice.dto.ItemDto;
import org.gene.myfirstservice.entity.ItemEntity;
import org.springframework.stereotype.Component;


@Component
public class ItemConverter {

    public ItemDto entityToDto(ItemEntity itemEntity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());
        itemDto.setPrice(itemEntity.getPrice());
        return itemDto;
    }

    public ItemEntity dtoToEntity(ItemDto itemDto) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDto.getId());
        itemEntity.setName(itemDto.getName());
        itemEntity.setPrice(itemDto.getPrice());
        return itemEntity;
    }
}