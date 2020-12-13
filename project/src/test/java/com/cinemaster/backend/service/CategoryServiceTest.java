package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.CategoryDto;
import com.cinemaster.backend.data.entity.Category;
import com.cinemaster.backend.data.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testCategoryService() {
        Category category = new Category();
        category.setName("Azione");
        categoryService.save(category);

        List<CategoryDto> dto = categoryService.findAll();
        Assert.assertEquals(1, dto.size());

        categoryService.delete(category);

        category = new Category();
        category.setName("Azione");
        categoryService.save(category);

        List<Category> categories = categoryService.findAllByNameContains("Azione").stream().map(categoryDto -> modelMapper.map(categoryDto, Category.class)).collect(Collectors.toList());
        category = categories.get(0);
        category.setName("Avventura");
        categoryService.update(category);

        categories = categoryService.findAllByNameContains("Azione").stream().map(categoryDto -> modelMapper.map(categoryDto, Category.class)).collect(Collectors.toList());
        Assert.assertEquals(0, categories.size());

        categories = categoryService.findAllByNameContains("Avventura").stream().map(categoryDto -> modelMapper.map(categoryDto, Category.class)).collect(Collectors.toList());
        Assert.assertEquals(1, categories.size());

        categoryService.delete(category);
    }
}
