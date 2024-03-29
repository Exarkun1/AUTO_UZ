package com.energizer.auto_uz.repositories;

import com.energizer.auto_uz.models.advertisements.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    @Modifying
    @Query("delete from Advertisement ad where ad.id = :id")
    void deleteWithId(long id);
}
