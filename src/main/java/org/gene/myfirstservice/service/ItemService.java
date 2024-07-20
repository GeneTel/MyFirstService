package org.gene.myfirstservice.service;

import org.gene.myfirstservice.converter.ItemConverter;
import org.gene.myfirstservice.dto.ItemDto;
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
        return itemConverter.entityToDto(itemRepository.getItemById(id));
    }

    public ItemDto createItem(ItemDto itemDto) {
        return itemConverter.entityToDto(itemRepository.createItem(itemConverter.dtoToEntity(itemDto)));
    }

    public ItemDto updateItem(Long id, ItemDto itemDto) {
        return itemConverter.entityToDto(itemRepository.updateItem(id, itemConverter.dtoToEntity(itemDto)));
    }

    public void deleteItem(Long id) {
        itemRepository.deleteItem(id);
    }
}