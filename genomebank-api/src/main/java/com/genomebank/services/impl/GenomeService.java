package com.genomebank.services.impl;

import com.genomebank.dto.in.GenomeInDTO;
import com.genomebank.dto.response.GenomeOutDTO;
import com.genomebank.entities.Genome;
import com.genomebank.repositories.GenomeRepository;
import com.genomebank.services.IGenomeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenomeService implements IGenomeService {

    private final GenomeRepository genomeRepository;

    public GenomeService(GenomeRepository genomeRepository) {
        this.genomeRepository = genomeRepository;
    }

    @Override
    public List<GenomeOutDTO> getAll(Long speciesId) {
        List<Genome> genomes = (speciesId != null)
                ? genomeRepository.findBySpeciesId(speciesId)
                : genomeRepository.findAll();
        return genomes.stream()
                .map(g -> new GenomeOutDTO(g.getId(), g.getVersion()))
                .toList();
    }

    @Override
    public GenomeOutDTO getById(Long id) {
        Genome g = genomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genome not found"));
        return new GenomeOutDTO(g.getId(), g.getVersion());
    }

    @Override
    public GenomeOutDTO create(GenomeInDTO dto) {
        Genome g = new Genome();
        g.setVersion(dto.getVersion());
        genomeRepository.save(g);
        return new GenomeOutDTO(g.getId(), g.getVersion());
    }

    @Override
    public GenomeOutDTO update(Long id, GenomeInDTO dto) {
        Genome g = genomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genome not found"));
        g.setVersion(dto.getVersion());
        genomeRepository.save(g);
        return new GenomeOutDTO(g.getId(), g.getVersion());
    }

    @Override
    public boolean delete(Long id) {
        if (genomeRepository.existsById(id)) {
            genomeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
