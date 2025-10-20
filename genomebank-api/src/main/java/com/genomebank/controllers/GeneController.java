package com.genomebank.controllers;

import com.genomebank.dto.in.GeneInDTO;
import com.genomebank.dto.response.GeneOutDTO;
import com.genomebank.services.IGeneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genes")
@RequiredArgsConstructor
public class GeneController {

    private final IGeneService geneService;

    // ✅ GET /genes → listar todos o filtrar por cromosoma, rango o símbolo
    @GetMapping
    public ResponseEntity<List<GeneOutDTO>> getAllGenes(
            @RequestParam(required = false) Long chromosomeId,
            @RequestParam(required = false) Long start,
            @RequestParam(required = false) Long end,
            @RequestParam(required = false) String symbol
    ) {
        return ResponseEntity.ok(geneService.getAllGenes(chromosomeId, start, end, symbol));
    }

    // ✅ GET /genes/{id} → obtener un gen específico
    @GetMapping("/{id}")
    public ResponseEntity<GeneOutDTO> getGeneById(@PathVariable Long id) {
        return ResponseEntity.ok(geneService.getGeneById(id));
    }

    // ✅ POST /genes → crear un nuevo gen
    @PostMapping
    public ResponseEntity<GeneOutDTO> createGene(@RequestBody GeneInDTO dto) {
        return ResponseEntity.ok(geneService.createGene(dto));
    }

    // ✅ PUT /genes/{id} → actualizar un gen
    @PutMapping("/{id}")
    public ResponseEntity<GeneOutDTO> updateGene(
            @PathVariable Long id,
            @RequestBody GeneInDTO dto
    ) {
        return ResponseEntity.ok(geneService.updateGene(id, dto));
    }

    // ✅ DELETE /genes/{id} → eliminar un gen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGene(@PathVariable Long id) {
        geneService.deleteGene(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ GET /genes/{id}/sequence → obtener secuencia del gen
    @GetMapping("/{id}/sequence")
    public ResponseEntity<String> getGeneSequence(@PathVariable Long id) {
        return ResponseEntity.ok(geneService.getGeneSequence(id));
    }

    // ✅ PUT /genes/{id}/sequence → actualizar secuencia del gen
    @PutMapping("/{id}/sequence")
    public ResponseEntity<GeneOutDTO> updateGeneSequence(
            @PathVariable Long id,
            @RequestBody String newSequence
    ) {
        return ResponseEntity.ok(geneService.updateGeneSequence(id, newSequence));
    }
}
