package com.energizer.auto_uz.models.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "feedback")
@Getter @Setter @NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_gen")
    @SequenceGenerator(name = "feedback_gen", sequenceName = "feedback_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "score")
    private int score;

    @Column(name = "note")
    private String note;

    @Column(name = "time_of_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfCreate;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Feedback(int score, String note, Person owner) {
        this.score = score;
        this.note = note;
        this.owner = owner;
        this.timeOfCreate = new Date();
    }
}
