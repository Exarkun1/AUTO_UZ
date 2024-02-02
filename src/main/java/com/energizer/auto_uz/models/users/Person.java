package com.energizer.auto_uz.models.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "person")
@Getter @Setter @NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_gen")
    @SequenceGenerator(name = "person_gen", sequenceName = "person_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "person")
    @Cascade({
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.MERGE
    })
    private List<Advertisement> advertisements;

    @ManyToMany
    @JoinTable(name = "favourite",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "advertisement_id")
    )
    private List<Advertisement> favorites;

    @OneToMany(mappedBy = "person")
    private List<Feedback> feedbacks;

    public void addAdvertisement(Advertisement advertisement) {
        if(advertisements == null) advertisements = new ArrayList<>();
        advertisement.setPerson(this);
        advertisements.add(advertisement);
    }

    public void addFavorite(Advertisement favourite) {
        if(favorites == null) favorites = new ArrayList<>();
        favorites.add(favourite);
    }

    public Person(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
