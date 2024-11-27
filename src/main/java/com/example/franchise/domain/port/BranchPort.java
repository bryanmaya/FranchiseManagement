package com.example.franchise.domain.port;

import com.example.franchise.domain.model.Branch;

import java.util.Optional;

public interface BranchPort {
    Branch save(Branch branch);
    Optional<Branch> findById(Long id);
    void deleteById(Long id);
}
