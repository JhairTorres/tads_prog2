package com.tads_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DataReportListaDEDTO {
    private String gender;
    private List<CityStructureDEDTO> cities;
    private int quantity;

}
