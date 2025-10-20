package com.genomebank.services.impl;

import com.genomebank.dto.in.GenomeInDTO;
import com.genomebank.dto.response.GenomeOutDTO;
import com.genomebank.entities.Genome;
import com.genomebank.entities.Species;
import com.genomebank.repositories.GenomeRepository;
import com.genomebank.repositories.SpeciesRepository;
import com.genomebank.services.IGenomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenomeService implements IGenomeService {

    private final GenomeRepository genomeRepository;
    private final SpeciesRepository speciesRepository;

    @Override
    public List<GenomeOutDTO> getAllGenomes(Long speciesId) {
        List<Genome> genomes;

        if (speciesId != null) {
            genomes = genomeRepository.findBySpeciesId(speciesId);
        } else {
            genomes = genomeRepository.findAll();
        }

        return genomes.stream()
                .map(g -> new GenomeOutDTO(
                        g.getId(),
                        g.getVersion(),
                        g.getSpecies().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public GenomeOutDTO getGenomeById(Long id) {
        Genome genome = genomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genoma no encontrado"));

        return new GenomeOutDTO(
                genome.getId(),
                genome.getVersion(),
                genome.getSpecies().getId()
        );
    }

    @Override
    public GenomeOutDTO createGenome(GenomeInDTO genomeInDTO) {
        Species species = speciesRepository.findById(genomeInDTO.getSpeciesId())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));

        Genome genome = new Genome(
                null,
                genomeInDTO.getVersion(),
                species
        );

        Genome saved = genomeRepository.save(genome);

        return new GenomeOutDTO(
                saved.getId(),
                saved.getVersion(),
                saved.getSpecies().getId()
        );
    }

    @Override
    public GenomeOutDTO updateGenome(Long id, GenomeInDTO genomeInDTO) {
        Genome genome = genomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genoma no encontrado"));

        Species species = speciesRepository.findById(genomeInDTO.getSpeciesId())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));

        genome.setVersion(genomeInDTO.getVersion());
        genome.setSpecies(species);

        Genome updated = genomeRepository.save(genome);

        return new GenomeOutDTO(
                updated.getId(),
                updated.getVersion(),
                updated.getSpecies().getId()
        );
    }

    @Override
    public void deleteGenome(Long id) {
        if (!genomeRepository.existsById(id)) {
            throw new RuntimeException("Genoma no encontrado");
        }
        genomeRepository.deleteById(id);
    }
}
