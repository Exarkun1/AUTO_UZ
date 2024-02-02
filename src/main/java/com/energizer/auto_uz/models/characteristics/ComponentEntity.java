package com.energizer.auto_uz.models.characteristics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "component")
@Getter @Setter @NoArgsConstructor
public class ComponentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "characteristic_gen")
    @SequenceGenerator(name = "characteristic_gen", sequenceName = "characteristic_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "characteristic_id", referencedColumnName = "id")
    private Characteristic characteristic;

    public ComponentEntity(String name) {
        this.name = name;
    }
}
