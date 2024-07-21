package org.gene.myfirstservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gene.myfirstservice.dto.OrderDto;
import org.gene.myfirstservice.dto.ProductDto;
import org.gene.myfirstservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setItems(new ArrayList<>());

        when(orderService.getOrderById(1L)).thenReturn(orderDto);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setItems(new ArrayList<>());

        when(orderService.createOrder(any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(orderService, times(1)).createOrder(any(OrderDto.class));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setItems(new ArrayList<>());

        when(orderService.updateOrder(eq(1L), any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(put("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(orderService, times(1)).updateOrder(eq(1L), any(OrderDto.class));
    }

    @Test
    public void testAddProductToOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setItems(new ArrayList<>());

        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");

        when(orderService.addProductToOrder(eq(1L), any(ProductDto.class))).thenReturn(orderDto);

        mockMvc.perform(patch("/orders/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDto)))
                .andExpect(status().isOk());

        verify(orderService, times(1)).addProductToOrder(eq(1L), any(ProductDto.class));
    }

    @Test
    public void testRemoveProductFromOrder() throws Exception {
        doNothing().when(orderService).removeProductFromOrder(eq(1L), eq(2L));

        mockMvc.perform(delete("/orders/1/products/2"))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).removeProductFromOrder(eq(1L), eq(2L));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).deleteOrder(1L);
    }
}
