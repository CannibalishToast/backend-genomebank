package com.genomebank.repositories;

import com.genomebank.entities.Chromosome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChromosomeRepository extends JpaRepository<Chromosome, Long> {
    List<Chromosome> findByGenomeId(Long genomeId);
}
