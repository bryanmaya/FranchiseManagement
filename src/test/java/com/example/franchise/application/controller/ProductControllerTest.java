package com.example.franchise.application.controller;

import com.example.franchise.adapter.controller.ProductController;
import com.example.franchise.adapter.mapper.ProductMapper;
import com.example.franchise.application.dto.ProductDTO;
import com.example.franchise.domain.model.Branch;
import com.example.franchise.domain.model.Product;
import com.example.franchise.domain.service.BranchService;
import com.example.franchise.domain.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private BranchService branchService;


    @MockBean
    private ProductMapper productMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO(null, "Test Product", 10,new Branch());
        Product product = new Product(1L, "Test Product", 10, new Branch());
        Branch branch = new Branch(null, "Test Branch", new ArrayList<>());

        Mockito.when(branchService.findById(1L)).thenReturn(Optional.of(branch));
        Mockito.when(productService.save(any(Product.class))).thenReturn(product);
        Mockito.when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(product);
        Mockito.when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(post("/api/franchises/1/branches/1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.stock").value(10));
    }

    @Test
    void updateProductStock() throws Exception {
        Product product = new Product(1L, "Test Product", 20, new Branch());
        ProductDTO productDTO = new ProductDTO(1L, "Test Product", 20, new Branch());

        Mockito.when(productService.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productService.save(any(Product.class))).thenReturn(product);
        Mockito.when(productMapper.toDTO(any(Product.class))).thenReturn(productDTO);

        mockMvc.perform(put("/api/franchises/1/branches/1/products/1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock").value(20));
    }

    @Test
    void deleteProduct() throws Exception {
        Product product = new Product(1L, "Test Product", 20, new Branch());
        Mockito.when(productService.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(delete("/api/franchises/1/branches/1/products/1"))
                .andExpect(status().isNoContent());
    }
}
