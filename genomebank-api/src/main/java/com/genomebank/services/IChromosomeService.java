package com.genomebank.services;

import com.genomebank.dto.in.ChromosomeInDTO;
import com.genomebank.dto.response.ChromosomeOutDTO;
import java.util.List;

public interface IChromosomeService {

    List<ChromosomeOutDTO> getAllChromosomes(Long genomeId);
    ChromosomeOutDTO getChromosomeById(Long id);
    ChromosomeOutDTO createChromosome(ChromosomeInDTO chromosomeInDTO);
    ChromosomeOutDTO updateChromosome(Long id, ChromosomeInDTO chromosomeInDTO);
    void deleteChromosome(Long id);
    String getFullSequence(Long id);
    String getSequenceRange(Long id, Long start, Long end);
    ChromosomeOutDTO updateSequence(Long id, String newSequence);
}
