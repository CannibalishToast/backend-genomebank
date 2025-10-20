package com.genomebank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="geneFunction")
public class GeneFunction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gene_id", nullable = false)
    private Gene gene;

    // Muchos registros pertenecen a una función
    @ManyToOne
    @JoinColumn(name = "function_id", nullable = false)
    private Function function;

}
