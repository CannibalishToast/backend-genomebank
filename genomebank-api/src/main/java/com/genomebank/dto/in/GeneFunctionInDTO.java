package com.genomebank.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GeneFunctionInDTO {
    @NotNull
    private Long geneId;

    @NotNull
    private Long functionId;

    private String evidence;
}
