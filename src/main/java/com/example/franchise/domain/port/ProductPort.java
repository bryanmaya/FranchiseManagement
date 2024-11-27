package com.example.franchise.domain.port;

import com.example.franchise.domain.model.Product;

import java.util.Optional;

public interface ProductPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    void deleteById(Long id);
}
