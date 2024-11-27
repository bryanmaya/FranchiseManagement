package com.example.franchise.infrastructure.adapter;

import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.port.BranchPort;
import com.example.franchise.infrastructure.repository.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchRepositoryAdapterTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchRepositoryAdapter branchRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adapterImplementsBranchPort() {
        assertTrue(branchRepositoryAdapter instanceof BranchPort);
    }

    @Test
    void saveBranchThroughAdapter() {
        Branch branch = new Branch(null, "Test Branch", null);
        when(branchRepository.save(branch)).thenReturn(branch);

        Branch savedBranch = branchRepositoryAdapter.save(branch);

        assertNotNull(savedBranch);
        assertEquals("Test Branch", savedBranch.getName());
        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void findBranchByIdThroughAdapter() {
        Branch branch = new Branch(1L, "Test Branch", null);
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        Optional<Branch> foundBranch = branchRepositoryAdapter.findById(1L);

        assertTrue(foundBranch.isPresent());
        assertEquals("Test Branch", foundBranch.get().getName());
        verify(branchRepository, times(1)).findById(1L);
    }

}
