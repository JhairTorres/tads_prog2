package com.tads_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SiblingStructureListDEDTO {
    private int quantity;
    private String siblingStatus;


    public SiblingStructureListDEDTO(String withBrothers, List<SiblingStructureListDEDTO> withBrothersList, int withBrothersCount) {
    }

    public SiblingStructureListDEDTO(int femaleCount, int siHermanosMujeres) {
    }

    public SiblingStructureListDEDTO(String hayHermanos, int siHermanosHombres) {
    }
}
