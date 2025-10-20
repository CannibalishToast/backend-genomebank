package com.genomebank.dto.response;

import lombok.Data;

@Data
public class ChromosomeOutDTO {
    private Long id;
    private String name;
    private Long length;
    private String sequence;
    private Long genomeId;
}
