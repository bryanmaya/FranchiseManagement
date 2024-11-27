package com.example.franchise.adapter.controller;

import com.example.franchise.application.dto.ProductDTO;
import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Product;
import com.example.franchise.domain.service.BranchService;
import com.example.franchise.domain.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/franchises/{franchiseId}/branches/{branchId}/products")
public class ProductController {

    private final BranchService branchService;
    private final ProductService productService;

    public ProductController(BranchService branchService, ProductService productService) {
        this.branchService = branchService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@PathVariable Long branchId, @RequestBody ProductDTO productDTO) {
        Optional<Branch> branchOptional = branchService.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setStock(productDTO.getStock());
            branch.addProduct(product);
            productService.save(product);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{productId}/stock")
    public ResponseEntity<Product> updateProductStock(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        return productService.findById(productId)
                .map(product -> {
                    product.setStock(productDTO.getStock());
                    return ResponseEntity.ok(productService.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{productId}/name")
    public ResponseEntity<Product> updateProductName(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        return productService.findById(productId)
                .map(product -> {
                    product.setName(productDTO.getName());
                    return ResponseEntity.ok(productService.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}
