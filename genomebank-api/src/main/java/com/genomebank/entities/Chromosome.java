package com.genomebank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="chromosome")
public class Chromosome {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="length")
    private Long length;

    @Column(name="sequence")
    private String sequence;

    @ManyToOne
    @JoinColumn(name = "genome_id", nullable = false)
    private Genome genome;
}
