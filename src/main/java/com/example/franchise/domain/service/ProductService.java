package com.example.franchise.domain.service;

import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Product;
import com.example.franchise.domain.port.BranchPort;
import com.example.franchise.domain.port.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductPort productPort;

    private final BranchPort branchPort;

    public Product save(Product product) {
        return productPort.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productPort.findById(id);
    }

    public void deleteById(Long id) {
        Product product = productPort.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Product not fount"));
        Branch branch = product.getBranch();
        branch.getProducts().remove(product);
        branchPort.save(branch);
        productPort.deleteById(id);
    }
}
