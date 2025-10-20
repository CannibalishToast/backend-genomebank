package com.genomebank.services.impl;

import com.genomebank.dto.in.GeneFunctionInDTO;
import com.genomebank.dto.response.GeneFunctionOutDTO;
import com.genomebank.entities.Function;
import com.genomebank.entities.Gene;
import com.genomebank.entities.GeneFunction;
import com.genomebank.repositories.FunctionRepository;
import com.genomebank.repositories.GeneFunctionRepository;
import com.genomebank.repositories.GeneRepository;
import com.genomebank.services.IGeneFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneFunctionService implements IGeneFunctionService {

    private final GeneFunctionRepository geneFunctionRepository;
    private final GeneRepository geneRepository;
    private final FunctionRepository functionRepository;

    @Override
    public List<GeneFunctionOutDTO> getFunctionsByGeneId(Long geneId) {
        // Validar existencia del gen
        geneRepository.findById(geneId)
                .orElseThrow(() -> new RuntimeException("Gen no encontrado"));

        return geneFunctionRepository.findByGeneId(geneId)
                .stream()
                .map(gf -> new GeneFunctionOutDTO(
                        gf.getId(),
                        gf.getGene().getId(),
                        gf.getFunction().getId(),
                        null // El campo 'evidence' aún no está implementado en la entidad
                ))
                .collect(Collectors.toList());
    }

    @Override
    public GeneFunctionOutDTO addFunctionToGene(GeneFunctionInDTO geneFunctionInDTO) {
        Gene gene = geneRepository.findById(geneFunctionInDTO.getGeneId())
                .orElseThrow(() -> new RuntimeException("Gen no encontrado"));

        Function function = functionRepository.findById(geneFunctionInDTO.getFunctionId())
                .orElseThrow(() -> new RuntimeException("Funcion no encontrada"));

        // Evitar duplicados
        if (geneFunctionRepository.findByGeneIdAndFunctionId(
                geneFunctionInDTO.getGeneId(), geneFunctionInDTO.getFunctionId()
        ).isPresent()) {
            throw new RuntimeException("La funcion ya esta asociada a este gen");
        }

        GeneFunction geneFunction = new GeneFunction(
                null,
                gene,
                function
        );

        GeneFunction saved = geneFunctionRepository.save(geneFunction);

        return new GeneFunctionOutDTO(
                saved.getId(),
                saved.getGene().getId(),
                saved.getFunction().getId(),
                geneFunctionInDTO.getEvidence()
        );
    }

    @Override
    public void removeFunctionFromGene(Long geneId, Long functionId) {
        // Verificar que exista la asociación
        if (geneFunctionRepository.findByGeneIdAndFunctionId(geneId, functionId).isEmpty()) {
            throw new RuntimeException("La asociacion no existe");
        }
        geneFunctionRepository.deleteByGeneIdAndFunctionId(geneId, functionId);
    }
}
