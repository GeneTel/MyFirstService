package org.gene.myfirstservice.controller;

import org.gene.myfirstservice.dto.ItemDto;
import org.gene.myfirstservice.entity.ItemEntity;
import org.gene.myfirstservice.repository.ItemRepository;
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
public class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        itemRepository.deleteAll();
        ItemEntity item = new ItemEntity();
        item.setName("Test Item");
        item.setPrice(10.0);
        itemRepository.save(item);
    }

    @Test
    public void getItemById_ShouldReturnItem() throws Exception {
        ItemEntity item = itemRepository.findAll().get(0);

        mockMvc.perform(get("/items/{id}", item.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Item"))
                .andExpect(jsonPath("$.price").value(10.0));
    }

    @Test
    public void createItem_ShouldCreateItem() throws Exception {
        ItemDto newItem = new ItemDto();
        newItem.setName("New Item");
        newItem.setPrice(20.0);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Item\",\"price\":20.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Item"))
                .andExpect(jsonPath("$.price").value(20.0));
    }
}
