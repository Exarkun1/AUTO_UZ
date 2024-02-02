package com.energizer.auto_uz.models.marks;

import com.energizer.auto_uz.models.characteristics.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "generation")
@Getter @Setter @NoArgsConstructor
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_gen")
    @SequenceGenerator(name = "mark_gen", sequenceName = "mark_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    /*@ManyToMany
    @JoinTable(name = "generation_corpus",
            joinColumns = @JoinColumn(name = "generation_id"),
            inverseJoinColumns = @JoinColumn(name = "corpus_id")
    )
    private List<ComponentEntity> components;*/
}
