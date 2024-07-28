package org.gene.myfirstservice.controller;


import org.gene.myfirstservice.dto.ProductDto;
import org.gene.myfirstservice.entity.ProductEntity;
import org.gene.myfirstservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setDescription("Test Description");
        productRepository.save(product);
    }

    @Test
    public void getProductById_ShouldReturnProduct() throws Exception {
        ProductEntity product = productRepository.findAll().get(0);

        mockMvc.perform(get("/products/{id}", product.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    public void createProduct_ShouldCreateProduct() throws Exception {
        ProductDto newProduct = new ProductDto();
        newProduct.setName("New Product");
        newProduct.setDescription("New Description");

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Product\",\"description\":\"New Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Product"))
                .andExpect(jsonPath("$.description").value("New Description"));
    }
}
