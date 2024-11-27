package com.example.franchise.domain.service;

import com.example.franchise.application.dto.TopProductDTO;
import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.model.Product;
import com.example.franchise.domain.port.BranchPort;
import com.example.franchise.domain.port.FranchisePort;
import com.example.franchise.domain.port.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductPort productPort;

    private final BranchPort branchPort;

    private final FranchisePort franchisePort;

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

    public List<TopProductDTO> getTopProductsByBranch(Long franchiseId) {
        Optional<Franchise> franchiseOptional = franchisePort.findById(franchiseId);

        if (franchiseOptional.isEmpty()) {
            throw new IllegalArgumentException("Franchise not found with id: " + franchiseId);
        }

        Franchise franchise = franchiseOptional.get();
        List<TopProductDTO> topProducts = new ArrayList<>();

        for (Branch branch : franchise.getBranches()) {
            branch.getProducts().stream()
                    .max(Comparator.comparingInt(Product::getStock)).ifPresent(topProduct ->
                            topProducts.add(new TopProductDTO(branch.getName(), topProduct.getName(), topProduct.getStock())));

        }

        return topProducts;
    }
}
