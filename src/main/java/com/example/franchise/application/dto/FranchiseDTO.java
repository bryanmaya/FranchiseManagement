package com.example.franchise.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseDTO {
    private Long id;
    private String name;
    private List<BranchDTO> branches;
}
