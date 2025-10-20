package com.genomebank.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneFunctionInDTO {
    @NotNull
    private Long geneId;

    @NotNull
    private Long functionId;

    private String evidence;
}
