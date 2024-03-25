package com.energizer.auto_uz.models.advertisements;

import com.energizer.auto_uz.models.characteristics.*;
import com.energizer.auto_uz.models.marks.Generation;
import com.energizer.auto_uz.models.users.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "advertisement")
@Getter @Setter @NoArgsConstructor
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advertisement_gen")
    @SequenceGenerator(name = "advertisement_gen", sequenceName = "advertisement_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "mileage")
    private Long mileage;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private long price;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_creation")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    private Generation generation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corpus_id", referencedColumnName = "id")
    private ComponentEntity corpus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    private ComponentEntity engine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drive_id", referencedColumnName = "id")
    private ComponentEntity drive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_id", referencedColumnName = "id")
    private ComponentEntity transmission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modification_id", referencedColumnName = "id")
    private ComponentEntity modification;

    @OneToMany(mappedBy = "advertisement")
    @Cascade({
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.MERGE
    })
    private List<AdvertisementPhoto> photos;

    public void addPhotos(List<AdvertisementPhoto> photoList) {
        if(photos == null) photos = new ArrayList<>();
        photoList.forEach(f -> f.setAdvertisement(this));
        photos.addAll(photoList);
    }

    public Advertisement(Long mileage, String description, Long price,
                         Generation generation, ComponentEntity corpus, ComponentEntity engine,
                         ComponentEntity drive, ComponentEntity transmission, ComponentEntity modification) {
        this.mileage = mileage;
        this.description = description;
        this.price = price;
        this.generation = generation;
        this.corpus = corpus;
        this.engine = engine;
        this.drive = drive;
        this.transmission = transmission;
        this.modification = modification;
        this.date = new Date();
    }
}
