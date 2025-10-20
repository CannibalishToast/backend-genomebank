package com.genomebank.services.impl;

import com.genomebank.dto.in.GeneInDTO;
import com.genomebank.dto.response.GeneOutDTO;
import com.genomebank.entities.Chromosome;
import com.genomebank.entities.Gene;
import com.genomebank.repositories.ChromosomeRepository;
import com.genomebank.repositories.GeneRepository;
import com.genomebank.services.IGeneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneService implements IGeneService {

    private final GeneRepository geneRepository;
    private final ChromosomeRepository chromosomeRepository;

    @Override
    public List<GeneOutDTO> getAllGenes(Long chromosomeId, Long start, Long end, String symbol) {
        List<Gene> genes;

        if (symbol != null) {
            genes = geneRepository.findBySymbolIgnoreCase(symbol)
                    .map(List::of)
                    .orElse(List.of());
        } else if (chromosomeId != null) {
            genes = geneRepository.findByChromosomeId(chromosomeId);
        } else if (start != null && end != null) {
            genes = geneRepository.findByStartPositionGreaterThanEqualAndEndPositionLessThanEqual(start, end);
        } else {
            genes = geneRepository.findAll();
        }

        return genes.stream()
                .map(this::mapToOutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GeneOutDTO getGeneById(Long id) {
        Gene gene = geneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gen no encontrado"));
        return mapToOutDTO(gene);
    }

    @Override
    public GeneOutDTO createGene(GeneInDTO geneInDTO) {
        Chromosome chromosome = chromosomeRepository.findById(geneInDTO.getChromosomeId())
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        if (geneInDTO.getStartPosition() >= geneInDTO.getEndPosition()) {
            throw new RuntimeException("La posición inicial debe ser menor que la final");
        }

        if (geneInDTO.getEndPosition() > chromosome.getLength()) {
            throw new RuntimeException("El rango del gen excede la longitud del cromosoma");
        }

        // Si no se proporciona secuencia, se extrae del cromosoma
        String sequence = geneInDTO.getSequence();
        if (sequence == null && chromosome.getSequence() != null) {
            sequence = chromosome.getSequence().substring(
                    geneInDTO.getStartPosition().intValue() - 1,
                    geneInDTO.getEndPosition().intValue()
            );
        }

        Gene gene = new Gene(
                null,
                geneInDTO.getSymbol(),
                geneInDTO.getStartPosition(),
                geneInDTO.getEndPosition(),
                geneInDTO.getStrand(),
                sequence,
                chromosome
        );

        Gene saved = geneRepository.save(gene);
        return mapToOutDTO(saved);
    }

    @Override
    public GeneOutDTO updateGene(Long id, GeneInDTO geneInDTO) {
        Gene gene = geneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gen no encontrado"));

        Chromosome chromosome = chromosomeRepository.findById(geneInDTO.getChromosomeId())
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        if (geneInDTO.getStartPosition() >= geneInDTO.getEndPosition()) {
            throw new RuntimeException("La posición inicial debe ser menor que la final");
        }

        if (geneInDTO.getEndPosition() > chromosome.getLength()) {
            throw new RuntimeException("El rango del gen excede la longitud del cromosoma");
        }

        String sequence = geneInDTO.getSequence();
        if (sequence == null && chromosome.getSequence() != null) {
            sequence = chromosome.getSequence().substring(
                    geneInDTO.getStartPosition().intValue() - 1,
                    geneInDTO.getEndPosition().intValue()
            );
        }

        gene.setSymbol(geneInDTO.getSymbol());
        gene.setStartPosition(geneInDTO.getStartPosition());
        gene.setEndPosition(geneInDTO.getEndPosition());
        gene.setStrand(geneInDTO.getStrand());
        gene.setSequence(sequence);
        gene.setChromosome(chromosome);

        Gene updated = geneRepository.save(gene);
        return mapToOutDTO(updated);
    }

    @Override
    public void deleteGene(Long id) {
        if (!geneRepository.existsById(id)) {
            throw new RuntimeException("Gen no encontrado");
        }
        geneRepository.deleteById(id);
    }

    @Override
    public String getGeneSequence(Long id) {
        Gene gene = geneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gen no encontrado"));
        return gene.getSequence();
    }

    @Override
    public GeneOutDTO updateGeneSequence(Long id, String newSequence) {
        Gene gene = geneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gen no encontrado"));

        // Validar longitud
        Long expectedLength = gene.getEndPosition() - gene.getStartPosition() + 1;
        if (newSequence.length() != expectedLength.intValue()) {
            throw new RuntimeException("La longitud de la secuencia no coincide con el rango del gen");
        }

        gene.setSequence(newSequence);
        Gene updated = geneRepository.save(gene);
        return mapToOutDTO(updated);
    }

    // 🔹 Método auxiliar
    private GeneOutDTO mapToOutDTO(Gene gene) {
        return new GeneOutDTO(
                gene.getId(),
                gene.getSymbol(),
                gene.getStartPosition(),
                gene.getEndPosition(),
                gene.getStrand(),
                gene.getSequence(),
                gene.getChromosome().getId()
        );
    }
}
