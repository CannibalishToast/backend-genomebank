package com.genomebank.controllers;


import com.genomebank.dto.in.ChromosomeInDTO;
import com.genomebank.dto.response.ChromosomeOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chromosomes")
@RequiredArgsConstructor
public class ChromosomeController {
    private final ChromosomeService chromosomeService;

    // GET /chromosomes → Listar todos o filtrar por genoma (?genomeId=)
    @GetMapping
    public ResponseEntity<List<ChromosomeOutDTO>> getAllChromosomes(@RequestParam(required = false) Long genomeId) {
        if (genomeId != null) {
            return ResponseEntity.ok(chromosomeService.findByGenomeId(genomeId));
        }
        return ResponseEntity.ok(chromosomeService.findAll());
    }

    // GET /chromosomes/{id} → Consultar un cromosoma específico
    @GetMapping("/{id}")
    public ResponseEntity<ChromosomeOutDTO> getChromosomeById(@PathVariable Long id) {
        return ResponseEntity.ok(chromosomeService.findById(id));
    }

    // POST /chromosomes → Crear un nuevo cromosoma (solo ADMIN)
    @PostMapping
    public ResponseEntity<ChromosomeOutDTO> createChromosome(@RequestBody ChromosomeInDTO dto) {
        return ResponseEntity.ok(chromosomeService.create(dto));
    }

    // PUT /chromosomes/{id} → Actualizar un cromosoma (solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<ChromosomeOutDTO> updateChromosome(@PathVariable Long id, @RequestBody ChromosomeInDTO dto) {
        return ResponseEntity.ok(chromosomeService.update(id, dto));
    }

    // DELETE /chromosomes/{id} → Eliminar un cromosoma (solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChromosome(@PathVariable Long id) {
        chromosomeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /chromosomes/{id}/sequence → Consultar la secuencia completa de ADN
    @GetMapping("/{id}/sequence")
    public ResponseEntity<String> getChromosomeSequence(@PathVariable Long id) {
        return ResponseEntity.ok(chromosomeService.getSequence(id));
    }

    // GET /chromosomes/{id}/sequence/range?start=&end= → Consultar subsecuencia por rango
    @GetMapping("/{id}/sequence/range")
    public ResponseEntity<String> getChromosomeSequenceRange(
            @PathVariable Long id,
            @RequestParam Long start,
            @RequestParam Long end
    ) {
        return ResponseEntity.ok(chromosomeService.getSequenceRange(id, start, end));
    }

    // PUT /chromosomes/{id}/sequence (solo ADMIN) → Registrar o actualizar la secuencia
    @PutMapping("/{id}/sequence")
    public ResponseEntity<String> updateChromosomeSequence(
            @PathVariable Long id,
            @RequestBody String newSequence
    ) {
        return ResponseEntity.ok(chromosomeService.updateSequence(id, newSequence));
    }

}
