package com.genomebank.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionInDTO {
    @NotBlank
    private String code;

    @NotBlank
    private String descriptiveName;

    @NotBlank
    private String category;
}
