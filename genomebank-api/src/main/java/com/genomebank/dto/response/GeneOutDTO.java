package com.genomebank.dto.response;

import lombok.Data;

@Data
public class GeneOutDTO {
    private Long id;
    private String symbol;
    private Long startPosition;
    private Long endPosition;
    private String strand;
    private String sequence;
    private Long chromosomeId;
}
