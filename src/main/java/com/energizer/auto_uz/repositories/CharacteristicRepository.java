package com.energizer.auto_uz.repositories;

import com.energizer.auto_uz.models.characteristics.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    Optional<Characteristic> findByType(String type);
}
