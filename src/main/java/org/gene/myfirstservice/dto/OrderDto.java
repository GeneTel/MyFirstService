package org.gene.myfirstservice.dto;

//import java.util.List;
//
//public class OrderDto {
//    private Long id;
//    private List<ItemDto> items;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public List<ItemDto> getItems() {
//        return items;
//    }
//
//    public void setItems(List<ItemDto> items) {
//        this.items = items;
//    }
//}

import java.util.List;

public class OrderDto {

    private Long id;
    private String customerName;
    private Double totalPrice;
    private List<ItemDto> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
