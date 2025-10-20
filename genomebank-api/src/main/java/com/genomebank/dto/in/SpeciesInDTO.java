package com.genomebank.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpeciesInDTO {
    @NotBlank
    private String scientificName;

    private String commonName;
}
