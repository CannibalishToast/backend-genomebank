package com.genomebank.services;

import com.genomebank.dto.in.SpeciesInDTO;
import com.genomebank.dto.response.SpeciesOutDTO;
import java.util.List;

public interface ISpeciesService {
    List<SpeciesOutDTO> getAll();
    SpeciesOutDTO getById(Long id);
    SpeciesOutDTO create(SpeciesInDTO dto);
    SpeciesOutDTO update(Long id, SpeciesInDTO dto);
    boolean delete(Long id);
}
