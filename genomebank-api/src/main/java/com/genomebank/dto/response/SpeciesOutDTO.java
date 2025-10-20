package com.genomebank.dto.response;

import lombok.Data;

@Data
public class SpeciesOutDTO {
    private Long id;
    private String scientificName;
    private String commonName;
}
