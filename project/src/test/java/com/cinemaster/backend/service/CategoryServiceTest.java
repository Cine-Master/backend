package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.CategoryDto;
import com.cinemaster.backend.data.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testCategoryService() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Azione");
        categoryService.save(categoryDto);

        List<CategoryDto> dto = categoryService.findAll();
        Assert.assertEquals(1, dto.size());

        categoryService.delete(categoryDto);

        categoryDto = new CategoryDto();
        categoryDto.setName("Azione");
        categoryService.save(categoryDto);

        List<CategoryDto> categories = categoryService.findAllByNameContains("Azione");
        categoryDto = categories.get(0);
        categoryDto.setName("Avventura");
        categoryService.update(categoryDto);

        categories = categoryService.findAllByNameContains("Azione");
        Assert.assertEquals(0, categories.size());

        categories = categoryService.findAllByNameContains("Avventura");
        Assert.assertEquals(1, categories.size());

        categoryService.delete(categoryDto);
    }
}
