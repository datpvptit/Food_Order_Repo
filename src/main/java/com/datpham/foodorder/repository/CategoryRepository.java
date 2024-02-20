package com.datpham.foodorder.repository;

import com.datpham.foodorder.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByNameCate(String name);

    void deleteByNameCate(String name);
}
