package com.notdefteri.uygulama.repository;

import com.notdefteri.uygulama.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findCategoryByUserId(Long userId);
    @Query(value ="SELECT * FROM category WHERE category_name = :categoryName", nativeQuery = true)
    List<Category> findCategoryByCategoryName(@Param("categoryName") String categoryName);
}
