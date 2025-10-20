package com.genomebank.services.impl;

import com.genomebank.dto.in.ChromosomeInDTO;
import com.genomebank.dto.response.ChromosomeOutDTO;
import com.genomebank.entities.Chromosome;
import com.genomebank.repositories.ChromosomeRepository;
import com.genomebank.services.IChromosomeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChromosomeService implements IChromosomeService {

    private final ChromosomeRepository chromosomeRepository;

    public ChromosomeService(ChromosomeRepository chromosomeRepository) {
        this.chromosomeRepository = chromosomeRepository;
    }

    @Override
    public List<ChromosomeOutDTO> getAll(Long genomeId) {
        List<Chromosome> chromosomes = (genomeId != null)
                ? chromosomeRepository.findByGenomeId(genomeId)
                : chromosomeRepository.findAll();

        return chromosomes.stream()
                .map(c -> new ChromosomeOutDTO(c.getId(), c.getName(), c.getLength(), c.getSequence()))
                .toList();
    }

    @Override
    public ChromosomeOutDTO getById(Long id) {
        Chromosome c = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chromosome not found"));
        return new ChromosomeOutDTO(c.getId(), c.getName(), c.getLength(), c.getSequence());
    }

    @Override
    public ChromosomeOutDTO create(ChromosomeInDTO dto) {
        Chromosome c = new Chromosome();
        c.setName(dto.getName());
        c.setLength(dto.getLength());
        c.setSequence(dto.getSequence());
        chromosomeRepository.save(c);
        return new ChromosomeOutDTO(c.getId(), c.getName(), c.getLength(), c.getSequence());
    }

    @Override
    public ChromosomeOutDTO update(Long id, ChromosomeInDTO dto) {
        Chromosome c = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chromosome not found"));
        c.setName(dto.getName());
        c.setLength(dto.getLength());
        c.setSequence(dto.getSequence());
        chromosomeRepository.save(c);
        return new ChromosomeOutDTO(c.getId(), c.getName(), c.getLength(), c.getSequence());
    }

    @Override
    public boolean delete(Long id) {
        if (chromosomeRepository.existsById(id)) {
            chromosomeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
