package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Entities.Order;
import com.example.OrderManagementSystem.Repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1L);
        order.setCustomerName("John Doe");
    }

    @Test
    void testCreateOrder() {
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.createOrder(order);

        assertNotNull(savedOrder);
        assertEquals("John Doe", savedOrder.getCustomerName());
    }

    @Test
    void testUpdateOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(1L, order);
        assertNotNull(updatedOrder);
        assertEquals("John Doe", updatedOrder.getCustomerName());
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void testOrderNotFoundException() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.updateOrder(1L, order);
        });

        assertEquals("Order not found", exception.getMessage());
    }
}
