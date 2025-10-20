package com.genomebank.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneFunctionOutDTO {
    private Long id;
    private Long geneId;
    private Long functionId;
    private String evidence;
}
