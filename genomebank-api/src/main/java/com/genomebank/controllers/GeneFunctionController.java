package com.genomebank.controllers;

import com.genomebank.dto.in.GeneFunctionInDTO;
import com.genomebank.dto.response.GeneFunctionOutDTO;
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

    // ✅ GET /genes/{geneId}/functions → Listar las funciones asociadas a un gen
    @GetMapping
    public ResponseEntity<List<GeneFunctionOutDTO>> getFunctionsByGene(@PathVariable Long geneId) {
        return ResponseEntity.ok(geneFunctionService.getFunctionsByGeneId(geneId));
    }

    //  POST /genes/{geneId}/functions → Asociar una nueva función a un gen
    @PostMapping
    public ResponseEntity<GeneFunctionOutDTO> addFunctionToGene(
            @PathVariable Long geneId,
            @RequestBody GeneFunctionInDTO geneFunctionInDTO
    ) {
        // Asegurar que el DTO contenga el ID del gen en la petición
        geneFunctionInDTO.setGeneId(geneId);

        GeneFunctionOutDTO created = geneFunctionService.addFunctionToGene(geneFunctionInDTO);
        return ResponseEntity.ok(created);
    }

    // ✅ DELETE /genes/{geneId}/functions/{functionId} → Eliminar una asociación
    @DeleteMapping("/{functionId}")
    public ResponseEntity<String> removeFunctionFromGene(
            @PathVariable Long geneId,
            @PathVariable Long functionId
    ) {
        geneFunctionService.removeFunctionFromGene(geneId, functionId);
        return ResponseEntity.ok("Asociacion eliminada correctamente.");
    }
}
