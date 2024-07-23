package org.gene.myfirstservice.repository;

import java.util.HashMap;
import java.util.Map;
import org.gene.myfirstservice.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {


    Map<Long, ItemEntity> items = new HashMap<>();

    public default ItemEntity getItemById(Long id) {
        return items.get(id);
    }

    public default ItemEntity createItem(ItemEntity itemEntity) {
        items.put(itemEntity.getId(), itemEntity);
        return itemEntity;
    }

    public default ItemEntity updateItem(Long id, ItemEntity itemEntity) {
        items.put(id, itemEntity);
        return itemEntity;
    }

    public default void deleteItem(Long id) {
        items.remove(id);
    }
}