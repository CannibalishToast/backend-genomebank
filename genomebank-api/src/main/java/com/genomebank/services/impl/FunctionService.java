package com.genomebank.services.impl;

import com.genomebank.dto.in.FunctionInDTO;
import com.genomebank.dto.response.FunctionOutDTO;
import com.genomebank.entities.Function;
import com.genomebank.repositories.FunctionRepository;
import com.genomebank.services.IFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FunctionService implements IFunctionService {

    private final FunctionRepository functionRepository;

    @Override
    public List<FunctionOutDTO> getAllFunctions(String code, String category) {
        List<Function> functions;

        if (code != null) {
            functions = functionRepository.findByCode(code)
                    .map(List::of)
                    .orElse(List.of());
        } else if (category != null) {
            try {
                Function.Category catEnum = Function.Category.valueOf(category.toUpperCase());
                functions = functionRepository.findByCategory(catEnum.name());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Categoria invalida. Use BP, MF o CC.");
            }
        } else {
            functions = functionRepository.findAll();
        }

        return functions.stream()
                .map(this::mapToOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FunctionOutDTO getFunctionById(Long id) {
        Function function = functionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcion no encontrada"));
        return mapToOutDTO(function);
    }

    @Override
    public FunctionOutDTO createFunction(FunctionInDTO functionInDTO) {
        // Validar si el codigo ya existe
        if (functionRepository.findByCode(functionInDTO.getCode()).isPresent()) {
            throw new RuntimeException("El codigo de la funcion ya existe");
        }

        Function.Category categoryEnum;
        try {
            categoryEnum = Function.Category.valueOf(functionInDTO.getCategory().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Categoria invalida. Use BP, MF o CC.");
        }

        Function function = new Function(
                null,
                functionInDTO.getCode(),
                functionInDTO.getDescriptiveName(),
                categoryEnum
        );

        Function saved = functionRepository.save(function);
        return mapToOutDTO(saved);
    }

    @Override
    public FunctionOutDTO updateFunction(Long id, FunctionInDTO functionInDTO) {
        Function function = functionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcion no encontrada"));

        Function.Category categoryEnum;
        try {
            categoryEnum = Function.Category.valueOf(functionInDTO.getCategory().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Categoria invalida. Use BP, MF o CC.");
        }

        function.setCode(functionInDTO.getCode());
        function.setDescriptiveName(functionInDTO.getDescriptiveName());
        function.setCategory(categoryEnum);

        Function updated = functionRepository.save(function);
        return mapToOutDTO(updated);
    }

    @Override
    public void deleteFunction(Long id) {
        if (!functionRepository.existsById(id)) {
            throw new RuntimeException("Funcion no encontrada");
        }
        functionRepository.deleteById(id);
    }

    // 🔹 Mapeo auxiliar
    private FunctionOutDTO mapToOutDTO(Function function) {
        return new FunctionOutDTO(
                function.getId(),
                function.getCode(),
                function.getDescriptiveName(),
                function.getCategory().name()
        );
    }
}
