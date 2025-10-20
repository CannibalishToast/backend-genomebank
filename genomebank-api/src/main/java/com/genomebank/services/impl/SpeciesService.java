package com.genomebank.services.impl;

import com.genomebank.dto.in.SpeciesInDTO;
import com.genomebank.dto.response.SpeciesOutDTO;
import com.genomebank.entities.Species;
import com.genomebank.repositories.SpeciesRepository;
import com.genomebank.services.ISpeciesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService implements ISpeciesService {

    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @Override
    public List<SpeciesOutDTO> getAll() {
        return speciesRepository.findAll().stream()
                .map(s -> new SpeciesOutDTO(s.getId(), s.getScientificName(), s.getCommonName()))
                .toList();
    }

    @Override
    public SpeciesOutDTO getById(Long id) {
        Species s = speciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Species not found"));
        return new SpeciesOutDTO(s.getId(), s.getScientificName(), s.getCommonName());
    }

    @Override
    public SpeciesOutDTO create(SpeciesInDTO dto) {
        Species s = new Species();
        s.setScientificName(dto.getScientificName());
        s.setCommonName(dto.getCommonName());
        speciesRepository.save(s);
        return new SpeciesOutDTO(s.getId(), s.getScientificName(), s.getCommonName());
    }

    @Override
    public SpeciesOutDTO update(Long id, SpeciesInDTO dto) {
        Species s = speciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Species not found"));
        s.setScientificName(dto.getScientificName());
        s.setCommonName(dto.getCommonName());
        speciesRepository.save(s);
        return new SpeciesOutDTO(s.getId(), s.getScientificName(), s.getCommonName());
    }

    @Override
    public boolean delete(Long id) {
        if (speciesRepository.existsById(id)) {
            speciesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
