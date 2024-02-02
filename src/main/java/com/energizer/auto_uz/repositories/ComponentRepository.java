package com.energizer.auto_uz.repositories;

import com.energizer.auto_uz.models.characteristics.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, Long> {
    @Query("select comp from ComponentEntity comp join fetch comp.characteristic ch where ch.type = :type")
    List<ComponentEntity> findWhereTypeIs(String type);

    @Query("select comp from ComponentEntity comp join fetch comp.characteristic ch where comp.id = :id and ch.type = :type")
    Optional<ComponentEntity> findWhereIdAndTypeIs(long id, String type);
}
