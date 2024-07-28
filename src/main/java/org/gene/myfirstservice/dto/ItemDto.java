package org.gene.myfirstservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {
    private Long id;
    private String name;
    private Double price;

}