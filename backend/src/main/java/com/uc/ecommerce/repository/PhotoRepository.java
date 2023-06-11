package com.uc.ecommerce.repository;

import com.uc.ecommerce.model.entity.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
    Optional<Photo> findByProduct_Id(Long productId);
}
