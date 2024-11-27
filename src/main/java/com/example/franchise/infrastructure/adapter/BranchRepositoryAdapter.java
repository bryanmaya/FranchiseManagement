package com.example.franchise.infrastructure.adapter;

import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.port.BranchPort;
import com.example.franchise.infrastructure.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BranchRepositoryAdapter implements BranchPort {

    private final BranchRepository branchRepository;

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Optional<Branch> findById(Long id) {
        return branchRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        branchRepository.deleteById(id);
    }
}
