package com.genomebank.dto.response;

import lombok.Data;

@Data
public class GeneFunctionOutDTO {
    private Long id;
    private Long geneId;
    private Long functionId;
    private String evidence;
}
