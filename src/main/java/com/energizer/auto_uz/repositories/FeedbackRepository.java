package com.energizer.auto_uz.repositories;

import com.energizer.auto_uz.models.users.Feedback;
import com.energizer.auto_uz.models.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByPersonAndOwner(Person person, Person owner);
    @Query("select avg(f.score) from Feedback f where f.person = :person")
    Double avgByScoreWherePerson(Person person);
}
