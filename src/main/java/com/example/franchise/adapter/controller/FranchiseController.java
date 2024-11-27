package com.example.franchise.adapter.controller;

import com.example.franchise.adapter.mapper.FranchiseMapper;
import com.example.franchise.application.dto.FranchiseDTO;
import com.example.franchise.application.dto.TopProductDTO;
import com.example.franchise.domain.service.FranchiseService;
import com.example.franchise.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<FranchiseDTO> addFranchise(@RequestBody FranchiseDTO franchiseDTO) {
        return ResponseEntity.ok(franchiseMapper.toDTO(
                franchiseService.save(franchiseMapper.toEntity(franchiseDTO))
        ));
    }

    @GetMapping
    public ResponseEntity<List<FranchiseDTO>> getAllFranchises() {
        return ResponseEntity.ok(
                franchiseService.findAll().stream()
                        .map(franchiseMapper::toDTO)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FranchiseDTO> updateFranchiseName(@PathVariable Long id, @RequestBody FranchiseDTO franchiseDTO) {
        return franchiseService.findById(id)
                .map(franchise -> {
                    franchise.setName(franchiseDTO.getName());
                    return ResponseEntity.ok(franchiseMapper.toDTO(franchiseService.save(franchise)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/top-products")
    public ResponseEntity<List<TopProductDTO>> getTopProductsByBranch(@PathVariable Long id) {
        List<TopProductDTO> topProducts = productService.getTopProductsByBranch(id);
        return ResponseEntity.ok(topProducts);
    }
}
