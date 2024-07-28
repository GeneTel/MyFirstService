package org.gene.myfirstservice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductEntity extends ItemEntity {
    private Long id;
    private String name;
    private String description;

}