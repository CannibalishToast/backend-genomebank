package com.genomebank.dto.response;

import lombok.Data;

@Data
public class GenomeOutDTO {
    private Long id;
    private String version;
    private Long speciesId;
}
