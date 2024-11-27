package com.example.franchise.adapter.mapper;

import com.example.franchise.application.dto.BranchDTO;
import com.example.franchise.domain.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    BranchDTO toDTO(Branch branch);

    Branch toEntity(BranchDTO branchDTO);
}
