package com.genomebank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="function")
public class Function {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="code", nullable = false)
    private String code;

    @Column(name="descriptiveName")
    private String descriptiveName;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;

    public enum Category {
        BP, MF, CC
    }

}
