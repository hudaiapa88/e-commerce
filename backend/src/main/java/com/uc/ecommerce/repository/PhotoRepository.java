package com.uc.ecommerce.repository;

import com.uc.ecommerce.model.entity.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
    Photo findByProduct_Id(Long productId);
}
