package com.genomebank.dto.in;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ChromosomeInDTO {
    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Long length;

    @Pattern(regexp = "[ACGTN]+", message = "Sequence must contain only A, C, G, T or N")
    private String sequence;

    @NotNull
    private Long genomeId;
}
