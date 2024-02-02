package com.energizer.auto_uz.models.marks;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "model")
@Getter @Setter @NoArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_gen")
    @SequenceGenerator(name = "mark_gen", sequenceName = "mark_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @OneToMany(mappedBy = "model")
    @Cascade(value = {
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.MERGE
    })
    private List<Generation> generations;

    public void addGenerations(List<Generation> generationList) {
        if(generations == null) generations = new ArrayList<>();
        generationList.forEach(g -> g.setModel(this));
        generations.addAll(generationList);
    }

    public Model(String name) {
        this.name = name;
    }
}
