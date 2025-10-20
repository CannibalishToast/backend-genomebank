package com.genomebank.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesInDTO {
    @NotBlank
    private String scientificName;

    private String commonName;
}
