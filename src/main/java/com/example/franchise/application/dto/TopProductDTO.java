package com.example.franchise.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopProductDTO {
    private String branchName;
    private String productName;
    private int stock;
}
