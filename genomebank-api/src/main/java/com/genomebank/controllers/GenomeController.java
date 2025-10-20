package com.genomebank.controllers;

import com.genomebank.dto.in.GenomeInDTO;
import com.genomebank.dto.response.GenomeOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genomes")
@RequiredArgsConstructor
public class GenomeController {

    private final GenomeService genomeService;

    // GET /genomes → Listar todos los genomas o filtrar por especie (?speciesId=)
    @GetMapping
    public ResponseEntity<List<GenomeOutDTO>> getAllGenomes(@RequestParam(required = false) Long speciesId) {
        if (speciesId != null) {
            return ResponseEntity.ok(genomeService.findBySpeciesId(speciesId));
        }
        return ResponseEntity.ok(genomeService.findAll());
    }

    // GET /genomes/{id} → Consultar un genoma específico
    @GetMapping("/{id}")
    public ResponseEntity<GenomeOutDTO> getGenomeById(@PathVariable Long id) {
        return ResponseEntity.ok(genomeService.findById(id));
    }

    // POST /genomes → Crear un nuevo genoma (solo ADMIN)
    @PostMapping
    public ResponseEntity<GenomeOutDTO> createGenome(@RequestBody GenomeInDTO dto) {
        return ResponseEntity.ok(genomeService.create(dto));
    }

    // PUT /genomes/{id} → Actualizar un genoma (solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<GenomeOutDTO> updateGenome(@PathVariable Long id, @RequestBody GenomeInDTO dto) {
        return ResponseEntity.ok(genomeService.update(id, dto));
    }

    // DELETE /genomes/{id} → Eliminar un genoma (solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenome(@PathVariable Long id) {
        genomeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
