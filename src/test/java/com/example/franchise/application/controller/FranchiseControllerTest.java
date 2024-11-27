package com.example.franchise.application.controller;

import com.example.franchise.adapter.controller.FranchiseController;
import com.example.franchise.adapter.mapper.FranchiseMapper;
import com.example.franchise.application.dto.FranchiseDTO;
import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.service.FranchiseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FranchiseController.class)
class FranchiseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FranchiseService franchiseService;

    @MockBean
    private FranchiseMapper franchiseMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addFranchise() throws Exception {
        FranchiseDTO franchiseDTO = new FranchiseDTO(null, "Test Franchise", Collections.emptyList());
        Franchise franchise = new Franchise(1L, "Test Franchise", Collections.emptyList());

        Mockito.when(franchiseService.save(any(Franchise.class))).thenReturn(franchise);
        Mockito.when(franchiseMapper.toEntity(any(FranchiseDTO.class))).thenReturn(franchise);
        Mockito.when(franchiseMapper.toDTO(any(Franchise.class))).thenReturn(franchiseDTO);

        mockMvc.perform(post("/api/franchises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(franchiseDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Franchise"));
    }

    @Test
    void getAllFranchises() throws Exception {
        Franchise franchise = new Franchise(1L, "Test Franchise", Collections.emptyList());
        FranchiseDTO franchiseDTO = new FranchiseDTO(1L, "Test Franchise", Collections.emptyList());

        Mockito.when(franchiseService.findAll()).thenReturn(List.of(franchise));
        Mockito.when(franchiseMapper.toDTO(any(Franchise.class))).thenReturn(franchiseDTO);

        mockMvc.perform(get("/api/franchises")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Franchise"));
    }

    @Test
    void updateFranchiseName() throws Exception {
        Franchise franchise = new Franchise(1L, "Updated Franchise", Collections.emptyList());
        FranchiseDTO franchiseDTO = new FranchiseDTO(1L, "Updated Franchise", Collections.emptyList());

        Mockito.when(franchiseService.findById(1L)).thenReturn(Optional.of(franchise));
        Mockito.when(franchiseService.save(any(Franchise.class))).thenReturn(franchise);
        Mockito.when(franchiseMapper.toDTO(any(Franchise.class))).thenReturn(franchiseDTO);

        mockMvc.perform(put("/api/franchises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(franchiseDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Franchise"));
    }
}
