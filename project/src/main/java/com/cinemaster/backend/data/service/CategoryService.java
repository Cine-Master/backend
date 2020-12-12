package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.CategoryDto;
import com.cinemaster.backend.data.entity.Category;

import java.util.List;

public interface CategoryService {

    void save(Category category);

    void update(Category category);

    void delete(Category category);

    List<CategoryDto> findAll();
}
