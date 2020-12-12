package com.cinemaster.backend.data.dao;

import com.cinemaster.backend.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryDao extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
}
