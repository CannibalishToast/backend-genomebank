package com.genomebank.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionOutDTO {
    private Long id;
    private String code;
    private String descriptiveName;
    private String category;
}
