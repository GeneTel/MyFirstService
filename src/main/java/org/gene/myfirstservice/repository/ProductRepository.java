package org.gene.myfirstservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.gene.myfirstservice.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Map<Long, ProductEntity> products = new HashMap<>();

    public default ProductEntity getProductById(Long id) {
        return products.get(id);
    }

    public default ProductEntity createProduct(ProductEntity productEntity) {
        products.put(productEntity.getId(), productEntity);
        return productEntity;
    }

    public default ProductEntity updateProduct(Long id, ProductEntity productEntity) {
        products.put(id, productEntity);
        return productEntity;
    }

    public default void deleteProduct(Long id) {
        products.remove(id);
    }
}