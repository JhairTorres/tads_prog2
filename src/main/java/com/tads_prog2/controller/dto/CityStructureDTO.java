package com.tads_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CityStructureDTO {
    private String city;
    private int quantity;

    public CityStructureDTO(String male, List<SiblingStructureListDEDTO> brothersMales, int maleCount) {
    }
}
