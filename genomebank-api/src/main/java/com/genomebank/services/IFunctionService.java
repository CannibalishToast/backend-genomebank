package com.genomebank.services;

import com.genomebank.dto.in.FunctionInDTO;
import com.genomebank.dto.response.FunctionOutDTO;
import java.util.List;

public interface IFunctionService {

    List<FunctionOutDTO> getAllFunctions(String code, String category);
    FunctionOutDTO getFunctionById(Long id);
    FunctionOutDTO createFunction(FunctionInDTO functionInDTO);
    FunctionOutDTO updateFunction(Long id, FunctionInDTO functionInDTO);
    void deleteFunction(Long id);
}
