package com.energizer.auto_uz.models.characteristics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characteristic")
@Getter @Setter @NoArgsConstructor
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "characteristic_gen")
    @SequenceGenerator(name = "characteristic_gen", sequenceName = "characteristic_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "characteristic")
    @Cascade({
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.MERGE
    })
    private List<ComponentEntity> components;

    public void addComponents(List<ComponentEntity> componentList) {
        if(components == null) components = new ArrayList<>();
        componentList.forEach(c -> c.setCharacteristic(this));
        components.addAll(componentList);
    }
}
