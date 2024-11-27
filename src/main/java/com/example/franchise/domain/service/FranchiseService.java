package com.example.franchise.domain.service;

import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.port.FranchisePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchisePort franchisePort;

    public Franchise save(Franchise franchise) {
        return franchisePort.save(franchise);
    }

    public Optional<Franchise> findById(Long id) {
        return franchisePort.findById(id);
    }

    public List<Franchise> findAll() {
        return franchisePort.findAll();
    }

}
