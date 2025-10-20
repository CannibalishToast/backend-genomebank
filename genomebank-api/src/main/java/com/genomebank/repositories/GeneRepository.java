package com.genomebank.repositories;

import com.genomebank.entities.Gene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneRepository extends JpaRepository<Gene, Long> {

    // Filtro por cromosoma (?chromosomeId=)
    List<Gene> findByChromosomeId(Long chromosomeId);

    // Buscar por símbolo exacto (?symbol=)
    Optional<Gene> findBySymbolIgnoreCase(String symbol);

    // Filtro por rango de posiciones (?start=, ?end=)
    List<Gene> findByStartPositionGreaterThanEqualAndEndPositionLessThanEqual(Long start, Long end);

    //Buscar genes por cromosoma y rango
    List<Gene> findByChromosomeIdAndStartPositionGreaterThanEqualAndEndPositionLessThanEqual(Long chromosomeId, Long start, Long end);

}

