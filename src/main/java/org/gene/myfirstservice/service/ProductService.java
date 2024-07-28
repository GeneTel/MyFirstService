package org.gene.myfirstservice.service;

import org.gene.myfirstservice.converter.ProductConverter;
import org.gene.myfirstservice.dto.ProductDto;
import org.gene.myfirstservice.entity.ProductEntity;
import org.gene.myfirstservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    public ProductDto getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        return productConverter.entityToDto(productEntity);
    }

    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = productConverter.dtoToEntity(productDto);
        ProductEntity savedProduct = productRepository.save(productEntity);
        return productConverter.entityToDto(savedProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity != null) {
            productEntity.setName(productDto.getName());
            productEntity.setDescription(productDto.getDescription());
            ProductEntity updatedProduct = productRepository.save(productEntity);
            return productConverter.entityToDto(updatedProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
