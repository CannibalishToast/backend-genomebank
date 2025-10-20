package com.genomebank.controllers;

import com.genomebank.dto.in.GenomeInDTO;
import com.genomebank.dto.response.GenomeOutDTO;
import com.genomebank.services.impl.GenomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genomes")
@RequiredArgsConstructor
public class GenomeController {

    private final GenomeService genomeService;

     // GET /genomes
     // Listar todos los genomas o filtrar por especie
     // Accesible para ADMIN y USER autenticados

    @GetMapping
    public ResponseEntity<List<GenomeOutDTO>> getAllGenomes(
            @RequestParam(required = false) Long speciesId
    ) {
        return ResponseEntity.ok(genomeService.getAllGenomes(speciesId));
    }

     // GET /genomes/{id}
     // Consultar un genoma en específico
     // Accesible para ADMIN y USER autenticados.

    @GetMapping("/{id}")
    public ResponseEntity<GenomeOutDTO> getGenomeById(@PathVariable Long id) {
        return ResponseEntity.ok(genomeService.getGenomeById(id));
    }

    
     // POST /genomes
     // Crear un nuevo genoma.
     // Solo rol ADMIN puede acceder

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenomeOutDTO> createGenome(@RequestBody GenomeInDTO dto) {
        GenomeOutDTO created = genomeService.createGenome(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /genomes/{id}
    // Actualizar un genoma existente
    // Solo se puede acceder con rol ADMIN

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenomeOutDTO> updateGenome(
            @PathVariable Long id,
            @RequestBody GenomeInDTO dto
    ) {
        return ResponseEntity.ok(genomeService.updateGenome(id, dto));
    }

    // DELETE /genomes/{id}
    // eliminar un genoma existente
    // Solo se puede acceder ocn rol admin

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteGenome(@PathVariable Long id) {
        genomeService.deleteGenome(id);
        return ResponseEntity.ok("Genoma eliminado correctamente.");
    }
}
