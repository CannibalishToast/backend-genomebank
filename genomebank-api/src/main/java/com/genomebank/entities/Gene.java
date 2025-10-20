package com.genomebank.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="gene")
public class Gene {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="symbol")
    private String symbol;

    @Column(name="startPosition")
    private Long startPosition;

    @Column(name="endPosition")
    private Long endPosition;

    @Column(name="strand")
    private String strand;

    @Column(name="sequence")
    private String sequence;

    @ManyToOne
    @JoinColumn(name = "chromosome_id", nullable = false)
    private Chromosome chromosome;
}
