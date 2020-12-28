package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.CategoryDto;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.service.CategoryService;
import com.cinemaster.backend.data.service.ShowService;
import com.cinemaster.backend.data.specification.ShowSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShowServiceTest {

    @Autowired
    ShowService showService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ModelMapper modelMapper;

//    @Test
//    public void testShow() {
//        ShowDto show = new ShowDto();
//        show.setName("Guardiani della Galassia");
//        show.setComingSoon(false);
//        showService.save(show);
//
//        show.setName("The Avengers");
//        showService.update(show);
//
//        List<ShowDto> shows = showService.findAllByFilter(new ShowSpecification.Filter("Guardiani"));
//        Assert.assertEquals(0, shows.size());
//
//        shows = showService.findAllByFilter(new ShowSpecification.Filter("the"));
//        Assert.assertEquals(1, shows.size());
//
//        CategoryDto action = new CategoryDto();
//        action.setName("Azione");
//        categoryService.save(action);
//
//        CategoryDto adventure = new CategoryDto();
//        adventure.setName("Avventura");
//        categoryService.save(adventure);
//
//        CategoryDto fantasy = new CategoryDto();
//        fantasy.setName("Fantasy");
//        categoryService.save(fantasy);
//
//        ShowDto show2 = new ShowDto();
//        show2.setName("Guardiani della Galassia");
//        show2.setComingSoon(false);
//        showService.save(show2);
//
//        ShowDto show3 = new ShowDto();
//        show3.setName("Harry Potter");
//        show3.setComingSoon(false);
//        showService.save(show3);
//
//        show.setCategories(new ArrayList<>());
//        show2.setCategories(new ArrayList<>());
//        show3.setCategories(new ArrayList<>());
//
//        show.getCategories().add(action);
//        showService.update(show);
//        shows = showService.findAllByFilter(new ShowSpecification.Filter(null, action.getName()));
//        Assert.assertEquals(1, shows.size());
//
//        show.getCategories().add(adventure);
//        showService.update(show);
//        shows = showService.findAllByFilter(new ShowSpecification.Filter(null, adventure.getName()));
//        Assert.assertEquals(1, shows.size());
//
//        show2.getCategories().add(adventure);
//        showService.update(show2);
//
//        show3.getCategories().add(fantasy);
//        showService.update(show3);
//
//        shows = showService.findAllByFilter(new ShowSpecification.Filter(null, adventure.getName()));
//        Assert.assertEquals(2, shows.size());
//
//        shows = showService.findAllByFilter(new ShowSpecification.Filter(null, action.getName(), fantasy.getName()));
//        Assert.assertEquals(2, shows.size());
//
//        shows = showService.findAllByFilter(new ShowSpecification.Filter(null, adventure.getName(), fantasy.getName()));
//        Assert.assertEquals(3, shows.size());
//
//        shows = showService.findAllByFilter(new ShowSpecification.Filter("Harry", adventure.getName()));
//        Assert.assertEquals(0, shows.size());
//
//        shows = showService.findAllByFilter(new ShowSpecification.Filter("Potter", adventure.getName(), fantasy.getName()));
//        Assert.assertEquals(1, shows.size());
//
//        showService.delete(show);
//        showService.delete(show2);
//        showService.delete(show3);
//    }
}
