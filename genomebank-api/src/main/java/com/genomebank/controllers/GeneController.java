package com.genomebank.controllers;


import com.genomebank.dto.response.GeneOutDTO;
import com.genomebank.services.impl.GeneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genes")
@RequiredArgsConstructor
public class GeneController {

    private final GeneService geneService;

    // GET /genes → Listar todos los genes, con filtros opcionales (?chromosomeId=, ?start=, ?end=, ?symbol=)
    @GetMapping
    public ResponseEntity<List<GeneOutDTO>> getAllGenes(
            @RequestParam(required = false) Long chromosomeId,
            @RequestParam(required = false) Long start,
            @RequestParam(required = false) Long end,
            @RequestParam(required = false) String symbol
    ) {
        return ResponseEntity.ok(geneService.findWithFilters(chromosomeId, start, end, symbol));
    }

    // GET /genes/{id} → Consultar un gen específico
    @GetMapping("/{id}")
    public ResponseEntity<GeneOutDTO> getGeneById(@PathVariable Long id) {
        return ResponseEntity.ok(geneService.findById(id));
    }

    // POST /genes → Registrar un nuevo gen (solo ADMIN)
    @PostMapping
    public ResponseEntity<GeneOutDTO> createGene(@RequestBody GeneInDTO dto) {
        return ResponseEntity.ok(geneService.create(dto));
    }

    // PUT /genes/{id} → Actualizar un gen (solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<GeneOutDTO> updateGene(@PathVariable Long id, @RequestBody GeneInDTO dto) {
        return ResponseEntity.ok(geneService.update(id, dto));
    }

    // DELETE /genes/{id} → Eliminar un gen (solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGene(@PathVariable Long id) {
        geneService.delete(id);
        return ResponseEntity.noContent().build();
    }


    // GET /genes/{id}/sequence → Consultar la secuencia del gen
    @GetMapping("/{id}/sequence")
    public ResponseEntity<String> getGeneSequence(@PathVariable Long id) {
        return ResponseEntity.ok(geneService.getSequence(id));
    }

    // PUT /genes/{id}/sequence (solo ADMIN) → Registrar o actualizar la secuencia de ADN del gen
    @PutMapping("/{id}/sequence")
    public ResponseEntity<String> updateGeneSequence(@PathVariable Long id, @RequestBody String newSequence) {
        return ResponseEntity.ok(geneService.updateSequence(id, newSequence));
    }
}
