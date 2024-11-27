package com.example.franchise.infrastructure.adapter;

import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.port.FranchisePort;
import com.example.franchise.infrastructure.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FranchiseRepositoryAdapter implements FranchisePort {

    private final FranchiseRepository franchiseRepository;

    @Override
    public Franchise save(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    @Override
    public Optional<Franchise> findById(Long id) {
        return franchiseRepository.findById(id);
    }

    @Override
    public List<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        franchiseRepository.deleteById(id);
    }
}
