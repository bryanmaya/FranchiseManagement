package com.example.franchise.adapter.mapper;

import com.example.franchise.application.dto.FranchiseDTO;
import com.example.franchise.domain.model.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {

    FranchiseMapper INSTANCE = Mappers.getMapper(FranchiseMapper.class);

    FranchiseDTO toDTO(Franchise franchise);

    Franchise toEntity(FranchiseDTO franchiseDTO);
}
