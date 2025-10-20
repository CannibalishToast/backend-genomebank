package com.genomebank.dto.in;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
