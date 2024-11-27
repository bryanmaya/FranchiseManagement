package com.example.franchise.domain.service;

import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.port.BranchPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchServiceTest {

    @Mock
    private BranchPort branchPort;

    @InjectMocks
    private BranchService branchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBranch() {
        Branch branch = new Branch(null, "Test Branch", null);
        when(branchPort.save(branch)).thenReturn(branch);

        Branch savedBranch = branchService.save(branch);

        assertNotNull(savedBranch);
        assertEquals("Test Branch", savedBranch.getName());
        verify(branchPort, times(1)).save(branch);
    }

    @Test
    void findBranchById() {
        Branch branch = new Branch(1L, "Test Branch", null);
        when(branchPort.findById(1L)).thenReturn(Optional.of(branch));

        Optional<Branch> foundBranch = branchService.findById(1L);

        assertTrue(foundBranch.isPresent());
        assertEquals("Test Branch", foundBranch.get().getName());
        verify(branchPort, times(1)).findById(1L);
    }

}
