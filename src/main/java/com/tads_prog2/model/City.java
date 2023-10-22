package com.tads_prog2.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    @NotEmpty
    private String code;
    @NotEmpty
    private String city;
}
