package org.gene.myfirstservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.gene.myfirstservice.converter.OrderConverter;
import org.gene.myfirstservice.dto.OrderDto;
import org.gene.myfirstservice.dto.ProductDto;
import org.gene.myfirstservice.entity.ItemEntity;
import org.gene.myfirstservice.entity.OrderEntity;
import org.gene.myfirstservice.entity.ProductEntity;
import org.gene.myfirstservice.repository.OrderRepository;
import org.gene.myfirstservice.repository.ProductRepository;
import org.gene.myfirstservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;


public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderConverter orderConverter;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrderById() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setItems(new ArrayList<>());

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setItems(new ArrayList<>());

        when(orderRepository.getOrderById(1L)).thenReturn(orderEntity);
        when(orderConverter.entityToDto(orderEntity)).thenReturn(orderDto);

        OrderDto result = orderService.getOrderById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderRepository, times(1)).getOrderById(1L);
    }

    @Test
    public void testCreateOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setItems(new ArrayList<>());

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setItems(new ArrayList<>());

        when(orderConverter.dtoToEntity(any(OrderDto.class))).thenReturn(orderEntity);
        when(orderRepository.createOrder(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderConverter.entityToDto(any(OrderEntity.class))).thenReturn(orderDto);

        OrderDto result = orderService.createOrder(orderDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderRepository, times(1)).createOrder(any(OrderEntity.class));
    }

    @Test
    public void testUpdateOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setItems(new ArrayList<>());

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setItems(new ArrayList<>());

        when(orderConverter.dtoToEntity(any(OrderDto.class))).thenReturn(orderEntity);
        when(orderRepository.updateOrder(eq(1L), any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderConverter.entityToDto(any(OrderEntity.class))).thenReturn(orderDto);

        OrderDto result = orderService.updateOrder(1L, orderDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderRepository, times(1)).updateOrder(eq(1L), any(OrderEntity.class));
    }

    @Test
    public void testAddProductToOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setItems(new ArrayList<>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(2L);
        productEntity.setName("Test Product");
        productEntity.setDescription("Test Description");

        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("Test Product");
        productDto.setDescription("Test Description");

        when(orderRepository.getOrderById(1L)).thenReturn(orderEntity);
        when(productRepository.getProductById(2L)).thenReturn(productEntity);
        when(orderRepository.updateOrder(anyLong(), any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderConverter.entityToDto(any(OrderEntity.class))).thenReturn(new OrderDto());

        OrderDto result = orderService.addProductToOrder(1L, productDto);

        assertNotNull(result);
        verify(orderRepository, times(1)).getOrderById(1L);
        verify(productRepository, times(1)).getProductById(2L);
        verify(orderRepository, times(1)).updateOrder(eq(1L), any(OrderEntity.class));
    }

    @Test
    public void testRemoveProductFromOrder() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(2L);

        List<ItemEntity> products = new ArrayList<>();
        products.add(productEntity);
        orderEntity.setItems(products);

        when(orderRepository.getOrderById(1L)).thenReturn(orderEntity);
        doNothing().when(orderRepository).updateOrder(anyLong(), any(OrderEntity.class));

        orderService.removeProductFromOrder(1L, 2L);

        assertEquals(0, orderEntity.getItems().size());
        verify(orderRepository, times(1)).getOrderById(1L);
        verify(orderRepository, times(1)).updateOrder(eq(1L), any(OrderEntity.class));
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderRepository).deleteOrder(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteOrder(1L);
    }
}