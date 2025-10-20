package com.genomebank.controllers;

import com.genomebank.dto.in.GeneFunctionInDTO;
import com.genomebank.dto.response.FunctionOutDTO;
import com.genomebank.services.impl.GeneFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genes/{geneId}/functions")
@RequiredArgsConstructor
public class GeneFunctionController {
    private final GeneFunctionService geneFunctionService;

    // GET /genes/{id}/functions → Listar las funciones asociadas a un gen
    @GetMapping
    public ResponseEntity<List<FunctionOutDTO>> getFunctionsByGene(@PathVariable Long geneId) {
        return ResponseEntity.ok(geneFunctionService.getFunctionsByGeneId(geneId));
    }

    // POST /genes/{id}/functions/{functionId} (solo ADMIN) → Asociar una función a un gen
    @PostMapping("/{functionId}")
    public ResponseEntity<String> associateFunctionToGene(
            @PathVariable Long geneId,
            @PathVariable Long functionId,
            @RequestBody(required = false) GeneFunctionInDTO dto
    ) {
        geneFunctionService.associateFunction(geneId, functionId, dto);
        return ResponseEntity.ok("Función asociada correctamente al gen.");
    }

    // DELETE /genes/{id}/functions/{functionId} (solo ADMIN) → Eliminar una asociación
    @DeleteMapping("/{functionId}")
    public ResponseEntity<String> removeFunctionFromGene(
            @PathVariable Long geneId,
            @PathVariable Long functionId
    ) {
        geneFunctionService.removeAssociation(geneId, functionId);
        return ResponseEntity.ok("Asociación eliminada correctamente.");
    }
}
