package com.uc.ecommerce.repository;


import com.uc.ecommerce.model.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    List<Category> findByParentNull();
    @Query(value = "select c from #{#entityName} c " +
            "left join fetch c.parent p " +
            "where p.id=:parentId"
    )
    List<Category> findByParent_Id(@Param("parentId") Long parentId);

    void deleteByParent_Id(Long parentId);

    Optional<Category> findByIdAndParentNull(Long categoryId);

    Optional<Category> findByTitleContainingIgnoreCaseAndParentNull(String lesson);

    List<Category> findByIdInAndParentNull(List<Long> categoryIds);
}
