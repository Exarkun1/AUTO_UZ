package com.energizer.auto_uz.repositories;

import com.energizer.auto_uz.models.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    List<Person> findByUsername(String username);
}
