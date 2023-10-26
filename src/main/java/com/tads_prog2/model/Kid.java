package com.tads_prog2.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Kid {
    @NotEmpty
    private String identification;
    @NotEmpty
    private String name;
    @Max(25)
    private byte age;
    @NotEmpty
    private String gender;
    @Valid
    private City cityname;
}
