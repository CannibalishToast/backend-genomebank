package com.genomebank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="genome")
public class Genome {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="version")
    private String version;

    @ManyToOne
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;
}
