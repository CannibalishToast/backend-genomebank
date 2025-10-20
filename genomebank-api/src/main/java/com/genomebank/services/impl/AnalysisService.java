package com.genomebank.services.impl;

import com.genomebank.dto.response.GeneAnalysisOutDTO;
import com.genomebank.dto.response.SequenceStatsOutDTO;
import com.genomebank.entities.Chromosome;
import com.genomebank.entities.Gene;
import com.genomebank.repositories.ChromosomeRepository;
import com.genomebank.repositories.GeneRepository;
import com.genomebank.services.IAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//prueba sincronizacion
@Service
@RequiredArgsConstructor
public class AnalysisService implements IAnalysisService {

    private final GeneRepository geneRepository;
    private final ChromosomeRepository chromosomeRepository;

    @Override
    public List<GeneAnalysisOutDTO> getGenesInRange(Long chromosomeId, Long start, Long end) {
        // Verificar que el cromosoma exista
        chromosomeRepository.findById(chromosomeId)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        // Buscar genes en el rango del cromosoma
        List<Gene> genes = geneRepository
                .findByChromosomeIdAndStartPositionGreaterThanEqualAndEndPositionLessThanEqual(
                        chromosomeId, start, end);

        // Mapear entidades a DTO
        return genes.stream()
                .map(g -> new GeneAnalysisOutDTO(
                        g.getId(),
                        g.getSymbol(),       // ← usa symbol, no name
                        g.getStartPosition(),
                        g.getEndPosition(),
                        g.getStrand()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public SequenceStatsOutDTO getSequenceStats(Long chromosomeId) {
        // Obtener el cromosoma
        Chromosome chromosome = chromosomeRepository.findById(chromosomeId)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        String sequence = chromosome.getSequence();

        if (sequence == null || sequence.isEmpty()) {
            throw new RuntimeException("El cromosoma no contiene secuencia disponible");
        }

        long length = sequence.length();

        // Calcular contenido GC
        long gcCount = sequence.chars()
                .filter(c -> c == 'G' || c == 'g' || c == 'C' || c == 'c')
                .count();

        double gcPercent = ((double) gcCount / length) * 100;

        // Contar genes asociados
        long geneCount = geneRepository.findByChromosomeId(chromosomeId).size();

        // Retornar DTO con los tres campos correctos
        return new SequenceStatsOutDTO(length, gcPercent, geneCount);
    }
}
