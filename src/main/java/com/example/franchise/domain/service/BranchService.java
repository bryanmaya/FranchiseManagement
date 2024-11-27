package com.example.franchise.domain.service;

import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.port.BranchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchPort branchPort;

    public Branch save(Branch branch) {
        return branchPort.save(branch);
    }

    public Optional<Branch> findById(Long id) {
        return branchPort.findById(id);
    }

    public void deleteById(Long id) {
        branchPort.deleteById(id);
    }
}
