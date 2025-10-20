package com.genomebank.repositories;

import com.genomebank.entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FunctionRepository extends JpaRepository<Function, Long>{
    // GET /functions?code= → Buscar por código exacto
    Optional<Function> findByCode(String code);

    // GET /functions?category= → Filtrar por categoría (BP, MF, CC)
    List<Function> findByCategory(String category);
}
