package com.genomebank.repositories;

import com.genomebank.entities.GeneFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneFunctionRepository extends JpaRepository<GeneFunction, Long> {
    // GET /genes/{id}/functions → Listar las funciones asociadas a un gen
    List<GeneFunction> findByGeneId(Long geneId);

    // POST /genes/{id}/functions/{functionId} → Verificar si ya existe antes de asociar
    Optional<GeneFunction> findByGeneIdAndFunctionId(Long geneId, Long functionId);

    // DELETE /genes/{id}/functions/{functionId} → Eliminar una asociación
    void deleteByGeneIdAndFunctionId(Long geneId, Long functionId);
}
