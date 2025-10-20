package com.genomebank.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChromosomeOutDTO {
    private Long id;
    private String name;
    private Long length;
    private String sequence;
    private Long genomeId;
}
