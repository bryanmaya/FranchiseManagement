package com.example.franchise.application.controller;

import com.example.franchise.adapter.controller.BranchController;
import com.example.franchise.adapter.mapper.BranchMapper;
import com.example.franchise.application.dto.BranchDTO;
import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Franchise;
import com.example.franchise.domain.service.BranchService;
import com.example.franchise.domain.service.FranchiseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BranchController.class)
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @MockBean
    private FranchiseService franchiseService;

    @MockBean
    private BranchMapper branchMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addBranch() throws Exception {
        BranchDTO branchDTO = new BranchDTO(null, "Test Branch", Collections.emptyList());
        Branch branch = new Branch(1L, "Test Branch", Collections.emptyList());
        Franchise franchise = new Franchise(1L, "Test Franchise", new ArrayList<>());

        Mockito.when(franchiseService.findById(1L)).thenReturn(Optional.of(franchise));
        Mockito.when(branchService.save(any(Branch.class))).thenReturn(branch);
        Mockito.when(branchMapper.toEntity(any(BranchDTO.class))).thenReturn(branch);
        Mockito.when(branchMapper.toDTO(any(Branch.class))).thenReturn(branchDTO);

        mockMvc.perform(post("/api/franchises/1/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(branchDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Branch"));
    }

    @Test
    void updateBranchName() throws Exception {
        Branch branch = new Branch(1L, "Updated Branch", Collections.emptyList());
        BranchDTO branchDTO = new BranchDTO(1L, "Updated Branch", Collections.emptyList());

        Mockito.when(branchService.findById(1L)).thenReturn(Optional.of(branch));
        Mockito.when(branchService.save(any(Branch.class))).thenReturn(branch);
        Mockito.when(branchMapper.toDTO(any(Branch.class))).thenReturn(branchDTO);

        mockMvc.perform(put("/api/franchises/1/branches/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(branchDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Branch"));
    }

}
