package org.gene.myfirstservice.controller;

import org.gene.myfirstservice.dto.OrderDto;
import org.gene.myfirstservice.entity.OrderEntity;
import org.gene.myfirstservice.repository.OrderRepository;
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
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        orderRepository.deleteAll();
        OrderEntity order = new OrderEntity();
        order.setCustomerName("John Doe");
        order.setTotalPrice(100.0);
        orderRepository.save(order);
    }

    @Test
    public void getOrderById_ShouldReturnOrder() throws Exception {
        OrderEntity order = orderRepository.findAll().get(0);

        mockMvc.perform(get("/orders/{id}", order.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.totalPrice").value(100.0));
    }

    @Test
    public void createOrder_ShouldCreateOrder() throws Exception {
        OrderDto newOrder = new OrderDto();
        newOrder.setCustomerName("Jane Doe");
        newOrder.setTotalPrice(200.0);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"Jane Doe\",\"totalPrice\":200.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Jane Doe"))
                .andExpect(jsonPath("$.totalPrice").value(200.0));
    }
}
