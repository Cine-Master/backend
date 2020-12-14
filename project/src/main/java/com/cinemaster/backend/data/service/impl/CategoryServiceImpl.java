package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.CategoryDao;
import com.cinemaster.backend.data.dto.CategoryDto;
import com.cinemaster.backend.data.entity.Category;
import com.cinemaster.backend.data.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryDao.saveAndFlush(category);
        categoryDto.setId(category.getId());
    }

    @Override
    public void update(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryDao.saveAndFlush(category);
    }

    @Override
    public void delete(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryDao.delete(category);
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        Optional<Category> optional = categoryDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(category -> modelMapper.map(category, CategoryDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryDao.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> findAllByNameContains(String name) {
        return categoryDao.findAllByNameContains(name).stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
