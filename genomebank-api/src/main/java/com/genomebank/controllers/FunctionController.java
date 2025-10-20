package com.genomebank.controllers;


import com.genomebank.dto.in.FunctionInDTO;
import com.genomebank.dto.response.FunctionOutDTO;
import com.genomebank.services.impl.FunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functions")
@RequiredArgsConstructor
public class FunctionController {

    private final FunctionService functionService;

    // GET /functions → Listar todas las funciones o filtrar por código o categoría (?code=, ?category=)
    @GetMapping
    public ResponseEntity<List<FunctionOutDTO>> getAllFunctions(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String category
    ) {
        return ResponseEntity.ok(functionService.findAllFiltered(code, category));
    }

    // GET /functions/{id} → Consultar una función específica
    @GetMapping("/{id}")
    public ResponseEntity<FunctionOutDTO> getFunctionById(@PathVariable Long id) {
        return ResponseEntity.ok(functionService.findById(id));
    }

    // POST /functions → Crear una nueva función (solo ADMIN)
    @PostMapping
    public ResponseEntity<FunctionOutDTO> createFunction(@RequestBody FunctionInDTO dto) {
        return ResponseEntity.ok(functionService.create(dto));
    }

    // PUT /functions/{id} → Actualizar una función (solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<FunctionOutDTO> updateFunction(@PathVariable Long id, @RequestBody FunctionInDTO dto) {
        return ResponseEntity.ok(functionService.update(id, dto));
    }

    // DELETE /functions/{id} → Eliminar una función (solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunction(@PathVariable Long id) {
        functionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
