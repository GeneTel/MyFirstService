package org.gene.myfirstservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.gene.myfirstservice.entity.ItemEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    private Map<Long, ItemEntity> items = new HashMap<>();

    public ItemEntity getItemById(Long id) {
        return items.get(id);
    }

    public ItemEntity createItem(ItemEntity itemEntity) {
        items.put(itemEntity.getId(), itemEntity);
        return itemEntity;
    }

    public ItemEntity updateItem(Long id, ItemEntity itemEntity) {
        items.put(id, itemEntity);
        return itemEntity;
    }

    public void deleteItem(Long id) {
        items.remove(id);
    }
}