package com.genomebank.dto.response;

import lombok.Data;

@Data
public class FunctionOutDTO {
    private Long id;
    private String code;
    private String descriptiveName;
    private String category;
}
