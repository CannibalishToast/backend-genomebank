package com.genomebank.dto.in;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GeneInDTO {
    @NotBlank
    private String symbol;

    @NotNull
    private Long startPosition;

    @NotNull
    private Long endPosition;

    @Pattern(regexp = "[+-]", message = "Strand must be '+' or '-'")
    private String strand;

    @NotNull
    private Long chromosomeId;

    private String sequence;
}
