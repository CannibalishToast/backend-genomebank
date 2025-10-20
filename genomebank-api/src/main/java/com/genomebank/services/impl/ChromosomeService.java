package com.genomebank.services.impl;

import com.genomebank.dto.in.ChromosomeInDTO;
import com.genomebank.dto.response.ChromosomeOutDTO;
import com.genomebank.entities.Chromosome;
import com.genomebank.entities.Genome;
import com.genomebank.repositories.ChromosomeRepository;
import com.genomebank.repositories.GenomeRepository;
import com.genomebank.services.IChromosomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChromosomeService implements IChromosomeService {

    private final ChromosomeRepository chromosomeRepository;
    private final GenomeRepository genomeRepository;

    @Override
    public List<ChromosomeOutDTO> getAllChromosomes(Long genomeId) {
        List<Chromosome> chromosomes = (genomeId != null)
                ? chromosomeRepository.findByGenomeId(genomeId)
                : chromosomeRepository.findAll();

        return chromosomes.stream()
                .map(c -> new ChromosomeOutDTO(
                        c.getId(),
                        c.getName(),
                        c.getLength(),
                        c.getSequence(),
                        c.getGenome().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ChromosomeOutDTO getChromosomeById(Long id) {
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        return new ChromosomeOutDTO(
                chromosome.getId(),
                chromosome.getName(),
                chromosome.getLength(),
                chromosome.getSequence(),
                chromosome.getGenome().getId()
        );
    }

    @Override
    public ChromosomeOutDTO createChromosome(ChromosomeInDTO chromosomeInDTO) {
        Genome genome = genomeRepository.findById(chromosomeInDTO.getGenomeId())
                .orElseThrow(() -> new RuntimeException("Genoma no encontrado"));

        // Validar que la longitud coincida con la secuencia
        if (chromosomeInDTO.getSequence() != null &&
                !chromosomeInDTO.getLength().equals((long) chromosomeInDTO.getSequence().length())) {
            throw new RuntimeException("La longitud no coincide con la secuencia");
        }

        Chromosome chromosome = new Chromosome(
                null,
                chromosomeInDTO.getName(),
                chromosomeInDTO.getLength(),
                chromosomeInDTO.getSequence(),
                genome
        );

        Chromosome saved = chromosomeRepository.save(chromosome);

        return new ChromosomeOutDTO(
                saved.getId(),
                saved.getName(),
                saved.getLength(),
                saved.getSequence(),
                saved.getGenome().getId()
        );
    }

    @Override
    public ChromosomeOutDTO updateChromosome(Long id, ChromosomeInDTO chromosomeInDTO) {
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        Genome genome = genomeRepository.findById(chromosomeInDTO.getGenomeId())
                .orElseThrow(() -> new RuntimeException("Genoma no encontrado"));

        if (chromosomeInDTO.getSequence() != null &&
                !chromosomeInDTO.getLength().equals((long) chromosomeInDTO.getSequence().length())) {
            throw new RuntimeException("La longitud no coincide con la secuencia");
        }

        chromosome.setName(chromosomeInDTO.getName());
        chromosome.setLength(chromosomeInDTO.getLength());
        chromosome.setSequence(chromosomeInDTO.getSequence());
        chromosome.setGenome(genome);

        Chromosome updated = chromosomeRepository.save(chromosome);

        return new ChromosomeOutDTO(
                updated.getId(),
                updated.getName(),
                updated.getLength(),
                updated.getSequence(),
                updated.getGenome().getId()
        );
    }

    @Override
    public void deleteChromosome(Long id) {
        if (!chromosomeRepository.existsById(id)) {
            throw new RuntimeException("Cromosoma no encontrado");
        }
        chromosomeRepository.deleteById(id);
    }

    @Override
    public String getFullSequence(Long id) {
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));
        return chromosome.getSequence();
    }

    @Override
    public String getSequenceRange(Long id, Long start, Long end) {
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        if (start < 1 || end > chromosome.getLength() || start > end) {
            throw new RuntimeException("Rango inválido");
        }

        // Substring usa índices base 0
        return chromosome.getSequence().substring(start.intValue() - 1, end.intValue());
    }

    @Override
    public ChromosomeOutDTO updateSequence(Long id, String newSequence) {
        Chromosome chromosome = chromosomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cromosoma no encontrado"));

        chromosome.setSequence(newSequence);
        chromosome.setLength((long) newSequence.length());

        Chromosome updated = chromosomeRepository.save(chromosome);

        return new ChromosomeOutDTO(
                updated.getId(),
                updated.getName(),
                updated.getLength(),
                updated.getSequence(),
                updated.getGenome().getId()
        );
    }
}
