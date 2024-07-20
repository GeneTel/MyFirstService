package org.gene.myfirstservice.service;

import org.gene.myfirstservice.converter.ProductConverter;
import org.gene.myfirstservice.dto.ProductDto;
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
        return productConverter.entityToDto(productRepository.getProductById(id));
    }

    public ProductDto createProduct(ProductDto productDto) {
        return productConverter.entityToDto(productRepository.createProduct(productConverter.dtoToEntity(productDto)));
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        return productConverter.entityToDto(productRepository.updateProduct(id, productConverter.dtoToEntity(productDto)));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}