package com.genomebank.services;

import com.genomebank.dto.in.SpeciesInDTO;
import com.genomebank.dto.response.SpeciesOutDTO;
import java.util.List;

public interface ISpeciesService {

    List<SpeciesOutDTO> getAllSpecies();
    SpeciesOutDTO getSpeciesById(Long id);
    SpeciesOutDTO createSpecies(SpeciesInDTO speciesInDTO);
    SpeciesOutDTO updateSpecies(Long id, SpeciesInDTO speciesInDTO);
    void deleteSpecies(Long id);
}
