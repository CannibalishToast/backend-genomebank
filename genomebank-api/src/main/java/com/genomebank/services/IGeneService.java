package com.genomebank.services;

import com.genomebank.dto.in.GeneInDTO;
import com.genomebank.dto.response.GeneOutDTO;

import java.util.List;

public interface IGeneService {

    List<GeneOutDTO> getAllGenes(Long chromosomeId, Long start, Long end, String symbol);
    GeneOutDTO getGeneById(Long id);
    GeneOutDTO createGene(GeneInDTO geneInDTO);
    GeneOutDTO updateGene(Long id, GeneInDTO geneInDTO);
    void deleteGene(Long id);
    String getGeneSequence(Long id);
    GeneOutDTO updateGeneSequence(Long id, String newSequence);
}
