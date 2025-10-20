package com.genomebank.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesInDTO {

    // not null
    @NotBlank(message = "El nombre científico es obligatorio")
    @Size(max = 150, message = "El nombre científico no debe superar los 150 caracteres")
    private String scientificName;

    // puede ser null, tiene limite de longitud
    @Size(max = 150, message = "El nombre común no debe superar los 150 caracteres")
    private String commonName;
}
