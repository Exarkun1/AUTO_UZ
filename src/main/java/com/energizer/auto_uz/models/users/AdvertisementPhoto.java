package com.energizer.auto_uz.models.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "advertisement_photo")
@Getter @Setter @NoArgsConstructor
public class AdvertisementPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_gen")
    @SequenceGenerator(name = "photo_gen", sequenceName = "photo_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "filename")
    private String filename;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", referencedColumnName = "id")
    private Advertisement advertisement;

    public AdvertisementPhoto(String filename) {
        this.filename = filename;
    }
}
