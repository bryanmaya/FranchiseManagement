package com.example.franchise.adapter.controller;

import com.example.franchise.application.dto.BranchDTO;
import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.service.BranchService;
import com.example.franchise.domain.service.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/franchises/{franchiseId}/branches")
public class BranchController {

    private final FranchiseService franchiseService;
    private final BranchService branchService;

    public BranchController(FranchiseService franchiseService, BranchService branchService) {
        this.franchiseService = franchiseService;
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<Branch> addBranch(@PathVariable Long franchiseId, @RequestBody BranchDTO branchDTO) {
        Optional<Franchise> franchiseOptional = franchiseService.findById(franchiseId);
        if (franchiseOptional.isPresent()) {
            Franchise franchise = franchiseOptional.get();
            Branch branch = new Branch();
            branch.setName(branchDTO.getName());
            franchise.getBranches().add(branch);
            franchiseService.save(franchise);
            return ResponseEntity.ok(branch);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<Branch> updateBranchName(@PathVariable Long branchId, @RequestBody BranchDTO branchDTO) {
        return branchService.findById(branchId)
                .map(branch -> {
                    branch.setName(branchDTO.getName());
                    return ResponseEntity.ok(branchService.save(branch));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
