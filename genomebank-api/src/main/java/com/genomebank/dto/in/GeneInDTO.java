package com.genomebank.dto.in;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
