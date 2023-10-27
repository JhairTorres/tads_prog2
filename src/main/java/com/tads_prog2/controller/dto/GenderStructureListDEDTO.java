package com.tads_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;
@AllArgsConstructor
@Data
public class GenderStructureListDEDTO {
    private String gender;
    private List<SiblingStructureListDEDTO> siblingStatus;
    private int quantity;
}
