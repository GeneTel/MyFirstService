package org.gene.myfirstservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.gene.myfirstservice.entity.ProductEntity;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository {
    private Map<Long, ProductEntity> products = new HashMap<>();

    public ProductEntity getProductById(Long id) {
        return products.get(id);
    }

    public ProductEntity createProduct(ProductEntity productEntity) {
        products.put(productEntity.getId(), productEntity);
        return productEntity;
    }

    public ProductEntity updateProduct(Long id, ProductEntity productEntity) {
        products.put(id, productEntity);
        return productEntity;
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }
}