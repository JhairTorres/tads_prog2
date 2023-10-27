package com.tads_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DataReportListaDEDTO {
    private String gender;
    private List<CityStructureDTO> city;

    private int total;


}
