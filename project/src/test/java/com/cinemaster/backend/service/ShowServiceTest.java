package com.cinemaster.backend.service;

import com.cinemaster.backend.data.entity.Category;
import com.cinemaster.backend.data.entity.Show;
import com.cinemaster.backend.data.service.CategoryService;
import com.cinemaster.backend.data.service.ShowService;
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
public class ShowServiceTest {

    @Autowired
    ShowService showService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testSaveShow() {
        Show show = new Show();
        show.setName("Guardiani della Galassia");
        show.setComingSoon(false);

        List<Category> categories = categoryService.findAll().stream().map(categoryDto -> modelMapper.map(categoryDto, Category.class)).collect(Collectors.toList());
        show.setCategories(categories);

//        Category category = new Category();
//        category.setName("Avventura");
//        categoryService.save(category);
//
//        List<Category> categories = new ArrayList<>();
//        categories.add(category);
//        show.setCategories(categories);

        showService.save(show);
    }
}
