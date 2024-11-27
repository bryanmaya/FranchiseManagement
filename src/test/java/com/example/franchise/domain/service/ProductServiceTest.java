package com.example.franchise.domain.service;

import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Product;
import com.example.franchise.domain.port.BranchPort;
import com.example.franchise.domain.port.ProductPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductPort productPort;

    @Mock
    private BranchPort branchPort;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProduct() {
        Product product = new Product(null, "Test Product", 10, new Branch());
        when(productPort.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(10, savedProduct.getStock());
        verify(productPort, times(1)).save(product);
    }

    @Test
    void findProductById() {
        Product product = new Product(1L, "Test Product", 10, new Branch());
        when(productPort.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Test Product", foundProduct.get().getName());
        verify(productPort, times(1)).findById(1L);
    }

    @Test
    void deleteProduct() {
        Product product = new Product(1L, "Test Product", 10, new Branch(1L, "prueba", new ArrayList<>()));
        when(productPort.findById(1L)).thenReturn(Optional.of(product));
        when(branchPort.save(new Branch())).thenReturn(new Branch());
        productService.deleteById(1L);

        verify(productPort, times(1)).deleteById(1L);
    }
}
