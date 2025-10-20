package com.genomebank.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SequenceStatsOutDTO {
    private Long length;      // Longitud total de la secuencia
    private Double gcPercent; // Porcentaje de G y C en la secuencia
    private Long geneCount;   // Número de genes en ese cromosoma
}
