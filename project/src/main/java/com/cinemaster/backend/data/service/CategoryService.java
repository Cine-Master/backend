package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.CategoryDto;
import com.cinemaster.backend.data.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void save(Category category);

    void update(Category category);

    void delete(Category category);

    Optional<CategoryDto> findById(Long id);

    List<CategoryDto> findAll();

    List<CategoryDto> findAllByNameContains(String name);
}
