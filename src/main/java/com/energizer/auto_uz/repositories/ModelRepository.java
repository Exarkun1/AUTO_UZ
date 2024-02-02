package com.energizer.auto_uz.repositories;

import com.energizer.auto_uz.models.marks.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
