package org.gene.myfirstservice.service;

import org.gene.myfirstservice.converter.ItemConverter;
import org.gene.myfirstservice.dto.ItemDto;
import org.gene.myfirstservice.entity.ItemEntity;
import org.gene.myfirstservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemConverter itemConverter;

    public ItemDto getItemById(Long id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);
        return itemConverter.entityToDto(itemEntity);
    }

    public ItemDto createItem(ItemDto itemDto) {
        ItemEntity itemEntity = itemConverter.dtoToEntity(itemDto);
        ItemEntity savedItem = itemRepository.save(itemEntity);
        return itemConverter.entityToDto(savedItem);
    }

    public ItemDto updateItem(Long id, ItemDto itemDto) {
        ItemEntity itemEntity = itemRepository.findById(id).orElse(null);
        if (itemEntity != null) {
            itemEntity.setName(itemDto.getName());
            itemEntity.setPrice(itemDto.getPrice());
            ItemEntity updatedItem = itemRepository.save(itemEntity);
            return itemConverter.entityToDto(updatedItem);
        }
        return null;
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
