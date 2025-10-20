package com.genomebank.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Campo obligatorio
    @Column(name = "scientificName", nullable = false, length = 150)
    private String scientificName;

    // Campo no obligatorio
    @Column(name = "commonName", length = 150)
    private String commonName;

    // relacion con Genome, uno a varios.
    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genome> genomes;
}