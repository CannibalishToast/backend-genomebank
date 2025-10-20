package com.genomebank.services;

import com.genomebank.dto.in.GeneFunctionInDTO;
import com.genomebank.dto.response.GeneFunctionOutDTO;
import java.util.List;

public interface IGeneFunctionService {

    List<GeneFunctionOutDTO> getFunctionsByGeneId(Long geneId);
    GeneFunctionOutDTO addFunctionToGene(GeneFunctionInDTO geneFunctionInDTO);
    void removeFunctionFromGene(Long geneId, Long functionId);
}
