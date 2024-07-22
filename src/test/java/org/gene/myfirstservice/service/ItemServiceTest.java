package org.gene.myfirstservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.gene.myfirstservice.converter.ItemConverter;
import org.gene.myfirstservice.dto.ItemDto;
import org.gene.myfirstservice.entity.ItemEntity;
import org.gene.myfirstservice.repository.ItemRepository;
import org.gene.myfirstservice.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemConverter itemConverter;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetItemById() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(1L);
        itemEntity.setName("Test Item");
        itemEntity.setPrice(100.0);

        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setName("Test Item");
        itemDto.setPrice(100.0);

        when(itemRepository.getItemById(1L)).thenReturn(itemEntity);
        when(itemConverter.entityToDto(itemEntity)).thenReturn(itemDto);

        ItemDto result = itemService.getItemById(1L);

        assertNotNull(result);
        assertEquals("Test Item", result.getName());
        verify(itemRepository, times(1)).getItemById(1L);
    }

    @Test
    public void testCreateItem() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(1L);
        itemEntity.setName("Test Item");
        itemEntity.setPrice(100.0);

        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setName("Test Item");
        itemDto.setPrice(100.0);

        when(itemConverter.dtoToEntity(any(ItemDto.class))).thenReturn(itemEntity);
        when(itemRepository.createItem(any(ItemEntity.class))).thenReturn(itemEntity);
        when(itemConverter.entityToDto(any(ItemEntity.class))).thenReturn(itemDto);

        ItemDto result = itemService.createItem(itemDto);

        assertNotNull(result);
        assertEquals("Test Item", result.getName());
        verify(itemRepository, times(1)).createItem(any(ItemEntity.class));
    }

    @Test
    public void testUpdateItem() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(1L);
        itemEntity.setName("Updated Item");
        itemEntity.setPrice(150.0);

        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setName("Updated Item");
        itemDto.setPrice(150.0);

        when(itemConverter.dtoToEntity(any(ItemDto.class))).thenReturn(itemEntity);
        when(itemRepository.updateItem(eq(1L), any(ItemEntity.class))).thenReturn(itemEntity);
        when(itemConverter.entityToDto(any(ItemEntity.class))).thenReturn(itemDto);

        ItemDto result = itemService.updateItem(1L, itemDto);

        assertNotNull(result);
        assertEquals("Updated Item", result.getName());
        verify(itemRepository, times(1)).updateItem(eq(1L), any(ItemEntity.class));
    }

    @Test
    public void testDeleteItem() {
        doNothing().when(itemRepository).deleteItem(1L);

        itemService.deleteItem(1L);

        verify(itemRepository, times(1)).deleteItem(1L);
    }
}