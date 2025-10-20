package com.genomebank.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FunctionInDTO {
    @NotBlank
    private String code;

    @NotBlank
    private String descriptiveName;

    @NotBlank
    private String category;
}
