package com.genomebank.controllers;


import com.genomebank.dto.in.SpeciesInDTO;
import com.genomebank.dto.response.SpeciesOutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
@RequiredArgsConstructor
public class SpeciesController {

    private final SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<List<SpeciesOutDTO>> getAllSpecies() {
        return ResponseEntity.ok(speciesService.findAll());
    }

    // GET /species/{id} → Consultar una especie específica
    @GetMapping("/{id}")
    public ResponseEntity<SpeciesOutDTO> getSpeciesById(@PathVariable Long id) {
        return ResponseEntity.ok(speciesService.findById(id));
    }

    // POST /species → Crear una nueva especie (solo ADMIN)
    @PostMapping
    public ResponseEntity<SpeciesOutDTO> createSpecies(@RequestBody SpeciesInDTO dto) {
        return ResponseEntity.ok(speciesService.create(dto));
    }

    // PUT /species/{id} → Actualizar una especie (solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<SpeciesOutDTO> updateSpecies(@PathVariable Long id, @RequestBody SpeciesInDTO dto) {
        return ResponseEntity.ok(speciesService.update(id, dto));
    }

    // DELETE /species/{id} → Eliminar una especie (solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable Long id) {
        speciesService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
