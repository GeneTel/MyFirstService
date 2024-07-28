package org.gene.myfirstservice.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDto {

    private Long id;
    private String customerName;
    private Double totalPrice;
    private List<ItemDto> items;

}
