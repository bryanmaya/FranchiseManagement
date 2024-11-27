package com.example.franchise.domain.port;

import com.example.franchise.domain.model.Franchise;

import java.util.List;
import java.util.Optional;

public interface FranchisePort {
    Franchise save(Franchise franchise);
    Optional<Franchise> findById(Long id);
    List<Franchise> findAll();
    void deleteById(Long id);
}
