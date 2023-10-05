package com.tads_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ResponseDTO {
    private int code;
    Object data;
    List<String> error;
}
