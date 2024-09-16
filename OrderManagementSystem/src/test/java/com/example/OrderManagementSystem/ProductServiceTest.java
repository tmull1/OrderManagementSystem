package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Entities.Product;
import com.example.OrderManagementSystem.Repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(999.99));
        product.setStock(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.createProduct(product);

        assertNotNull(savedProduct);
        assertEquals("Laptop", savedProduct.getName());
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(1L, product);
        assertNotNull(updatedProduct);
        assertEquals("Laptop", updatedProduct.getName());
        assertEquals(BigDecimal.valueOf(999.99), updatedProduct.getPrice());
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(1L, product);
        });

        assertEquals("Product not found", exception.getMessage());
    }
}

