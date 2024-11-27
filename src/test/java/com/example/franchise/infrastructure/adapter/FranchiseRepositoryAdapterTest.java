package com.example.franchise.infrastructure.adapter;

import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.port.FranchisePort;
import com.example.franchise.infrastructure.repository.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FranchiseRepositoryAdapterTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private FranchiseRepositoryAdapter franchiseRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adapterImplementsPort() {
        assertTrue(franchiseRepositoryAdapter instanceof FranchisePort);
    }

    @Test
    void saveFranchiseThroughAdapter() {
        Franchise franchise = new Franchise(null, "Test Franchise", null);
        when(franchiseRepository.save(franchise)).thenReturn(franchise);

        Franchise savedFranchise = franchiseRepositoryAdapter.save(franchise);

        assertNotNull(savedFranchise);
        assertEquals("Test Franchise", savedFranchise.getName());
        verify(franchiseRepository, times(1)).save(franchise);
    }

    @Test
    void findFranchiseByIdThroughAdapter() {
        Franchise franchise = new Franchise(1L, "Test Franchise", null);
        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));

        Optional<Franchise> foundFranchise = franchiseRepositoryAdapter.findById(1L);

        assertTrue(foundFranchise.isPresent());
        assertEquals("Test Franchise", foundFranchise.get().getName());
        verify(franchiseRepository, times(1)).findById(1L);
    }
}
