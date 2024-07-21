package org.gene.myfirstservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.gene.myfirstservice.converter.ProductConverter;
import org.gene.myfirstservice.dto.ProductDto;
import org.gene.myfirstservice.entity.ProductEntity;
import org.gene.myfirstservice.repository.ProductRepository;
import org.gene.myfirstservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Test Product");
        productEntity.setDescription("Test Description");

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");

        when(productRepository.getProductById(1L)).thenReturn(productEntity);
        when(productConverter.entityToDto(productEntity)).thenReturn(productDto);

        ProductDto result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(productRepository, times(1)).getProductById(1L);
    }

    @Test
    public void testCreateProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Test Product");
        productEntity.setDescription("Test Description");

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");

        when(productConverter.dtoToEntity(any(ProductDto.class))).thenReturn(productEntity);
        when(productRepository.createProduct(any(ProductEntity.class))).thenReturn(productEntity);
        when(productConverter.entityToDto(any(ProductEntity.class))).thenReturn(productDto);

        ProductDto result = productService.createProduct(productDto);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(productRepository, times(1)).createProduct(any(ProductEntity.class));
    }

    @Test
    public void testUpdateProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Updated Product");
        productEntity.setDescription("Updated Description");

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Updated Product");
        productDto.setDescription("Updated Description");

        when(productConverter.dtoToEntity(any(ProductDto.class))).thenReturn(productEntity);
        when(productRepository.updateProduct(eq(1L), any(ProductEntity.class))).thenReturn(productEntity);
        when(productConverter.entityToDto(any(ProductEntity.class))).thenReturn(productDto);

        ProductDto result = productService.updateProduct(1L, productDto);

        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        verify(productRepository, times(1)).updateProduct(eq(1L), any(ProductEntity.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteProduct(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteProduct(1L);
    }
}
