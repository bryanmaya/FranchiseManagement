package com.example.franchise.application.dto;

import com.example.franchise.domain.model.Branch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private int stock;
    @JsonIgnore
    private Branch branch;
}
