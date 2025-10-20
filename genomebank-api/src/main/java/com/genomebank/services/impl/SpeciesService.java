package com.genomebank.services.impl;

import com.genomebank.dto.in.SpeciesInDTO;
import com.genomebank.dto.response.SpeciesOutDTO;
import com.genomebank.entities.Species;
import com.genomebank.repositories.SpeciesRepository;
import com.genomebank.services.ISpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpeciesService implements ISpeciesService {

    private final SpeciesRepository speciesRepository;

    @Override
    public List<SpeciesOutDTO> getAllSpecies() {
        return speciesRepository.findAll()
                .stream()
                .map(species -> new SpeciesOutDTO(
                        species.getId(),
                        species.getScientificName(),
                        species.getCommonName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public SpeciesOutDTO getSpeciesById(Long id) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));
        return new SpeciesOutDTO(
                species.getId(),
                species.getScientificName(),
                species.getCommonName()
        );
    }

    @Override
    public SpeciesOutDTO createSpecies(SpeciesInDTO speciesInDTO) {
        // crea nueva instancia usando constructor vacio
        Species species = new Species();
        species.setScientificName(speciesInDTO.getScientificName());
        species.setCommonName(speciesInDTO.getCommonName());

        Species saved = speciesRepository.save(species);

        return new SpeciesOutDTO(
                saved.getId(),
                saved.getScientificName(),
                saved.getCommonName()
        );
    }

    @Override
    public SpeciesOutDTO updateSpecies(Long id, SpeciesInDTO speciesInDTO) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));

        species.setScientificName(speciesInDTO.getScientificName());
        species.setCommonName(speciesInDTO.getCommonName());

        Species updated = speciesRepository.save(species);

        return new SpeciesOutDTO(
                updated.getId(),
                updated.getScientificName(),
                updated.getCommonName()
        );
    }

    @Override
    public void deleteSpecies(Long id) {
        if (!speciesRepository.existsById(id)) {
            throw new RuntimeException("Especie no encontrada");
        }
        speciesRepository.deleteById(id);
    }
}
