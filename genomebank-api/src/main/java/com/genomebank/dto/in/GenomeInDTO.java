package com.genomebank.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GenomeInDTO {
    @NotBlank
    private String version;

    @NotNull
    private Long speciesId;
}
