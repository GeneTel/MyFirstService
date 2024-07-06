package org.gene.myfirstservice.dto;

import java.util.List;

public class OrderDto {
    private Long id;
    private List<ItemDto> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}