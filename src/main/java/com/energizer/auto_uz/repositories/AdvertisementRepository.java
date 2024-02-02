package com.energizer.auto_uz.repositories;

import com.energizer.auto_uz.models.users.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
