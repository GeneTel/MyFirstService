package org.gene.myfirstservice.converter;

import org.gene.myfirstservice.dto.ProductDto;
import org.gene.myfirstservice.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductDto entityToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setDescription(productEntity.getDescription());
        return productDto;
    }

    public ProductEntity dtoToEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDto.getId());
        productEntity.setName(productDto.getName());
        productEntity.setDescription(productDto.getDescription());
        return productEntity;
    }
}