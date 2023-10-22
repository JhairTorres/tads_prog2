package com.tads_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DataStructureDTO {
    private String City;
    private int quantity;
    private List<GenderStructureDTO> gender;


}
