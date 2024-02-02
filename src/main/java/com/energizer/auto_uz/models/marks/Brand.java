package com.energizer.auto_uz.models.marks;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
@Getter @Setter @NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_gen")
    @SequenceGenerator(name = "mark_gen", sequenceName = "mark_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    @Cascade(value = {
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.MERGE
    })
    private List<Model> models;

    public Brand(String name) {
        this.name = name;
    }

    public void addModels(List<Model> modelList) {
        if(models == null) models = new ArrayList<>();
        modelList.forEach(m -> m.setBrand(this));
        models.addAll(modelList);
    }
}
