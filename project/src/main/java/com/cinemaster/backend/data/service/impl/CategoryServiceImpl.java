package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.CategoryDao;
import com.cinemaster.backend.data.dto.CategoryDto;
import com.cinemaster.backend.data.entity.Category;
import com.cinemaster.backend.data.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(Category category) {
        categoryDao.saveAndFlush(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.saveAndFlush(category);
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryDao.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
