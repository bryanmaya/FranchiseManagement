package com.example.franchise.infrastructure.adapter;

import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Product;
import com.example.franchise.domain.port.ProductPort;
import com.example.franchise.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductRepositoryAdapter productRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adapterImplementsProductPort() {
        assertTrue(productRepositoryAdapter instanceof ProductPort);
    }

    @Test
    void saveProductThroughAdapter() {
        Product product = new Product(null, "Test Product", 10, new Branch());
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productRepositoryAdapter.save(product);

        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(10, savedProduct.getStock());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void findProductByIdThroughAdapter() {
        Product product = new Product(1L, "Test Product", 10, new Branch());
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productRepositoryAdapter.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Test Product", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void deleteProductThroughAdapter() {
        doNothing().when(productRepository).deleteById(1L);

        productRepositoryAdapter.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
