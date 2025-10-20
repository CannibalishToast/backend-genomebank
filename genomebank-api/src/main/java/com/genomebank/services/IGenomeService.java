package com.genomebank.services;

import com.genomebank.dto.in.GenomeInDTO;
import com.genomebank.dto.response.GenomeOutDTO;
import java.util.List;

public interface IGenomeService {

    List<GenomeOutDTO> getAllGenomes(Long speciesId);
    GenomeOutDTO getGenomeById(Long id);
    GenomeOutDTO createGenome(GenomeInDTO genomeInDTO);
    GenomeOutDTO updateGenome(Long id, GenomeInDTO genomeInDTO);
    void deleteGenome(Long id);
}
