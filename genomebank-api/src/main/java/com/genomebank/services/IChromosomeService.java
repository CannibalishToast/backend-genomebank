package com.genomebank.services;

import com.genomebank.dto.in.ChromosomeInDTO;
import com.genomebank.dto.response.ChromosomeOutDTO;
import java.util.List;

public interface IChromosomeService {
    List<ChromosomeOutDTO> getAll(Long genomeId);
    ChromosomeOutDTO getById(Long id);
    ChromosomeOutDTO create(ChromosomeInDTO dto);
    ChromosomeOutDTO update(Long id, ChromosomeInDTO dto);
    boolean delete(Long id);
}
