package com.genomebank.services;

import com.genomebank.dto.in.GenomeInDTO;
import com.genomebank.dto.response.GenomeOutDTO;
import java.util.List;

public interface IGenomeService {
    List<GenomeOutDTO> getAll(Long speciesId);
    GenomeOutDTO getById(Long id);
    GenomeOutDTO create(GenomeInDTO dto);
    GenomeOutDTO update(Long id, GenomeInDTO dto);
    boolean delete(Long id);
}
