package com.example.franchise.domain.service;

import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.port.FranchisePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FranchiseServiceTest {

    @Mock
    private FranchisePort franchisePort;

    @InjectMocks
    private FranchiseService franchiseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFranchiseUsingPort() {
        Franchise franchise = new Franchise(null, "Test Franchise", null);
        when(franchisePort.save(franchise)).thenReturn(franchise);

        Franchise savedFranchise = franchiseService.save(franchise);

        assertNotNull(savedFranchise);
        assertEquals("Test Franchise", savedFranchise.getName());
        verify(franchisePort, times(1)).save(franchise);
    }

    @Test
    void findFranchiseByIdUsingPort() {
        Franchise franchise = new Franchise(1L, "Test Franchise", null);
        when(franchisePort.findById(1L)).thenReturn(Optional.of(franchise));

        Optional<Franchise> foundFranchise = franchiseService.findById(1L);

        assertTrue(foundFranchise.isPresent());
        assertEquals("Test Franchise", foundFranchise.get().getName());
        verify(franchisePort, times(1)).findById(1L);
    }

    @Test
    void findAllFranchisesUsingPort() {
        Franchise franchise = new Franchise(1L, "Test Franchise", null);
        when(franchisePort.findAll()).thenReturn(List.of(franchise));

        List<Franchise> franchises = franchiseService.findAll();

        assertNotNull(franchises);
        assertEquals(1, franchises.size());
        verify(franchisePort, times(1)).findAll();
    }
}
