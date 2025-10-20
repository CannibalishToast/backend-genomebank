package com.genomebank.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenomeOutDTO {
    private Long id;
    private String version;
    private Long speciesId;
}
