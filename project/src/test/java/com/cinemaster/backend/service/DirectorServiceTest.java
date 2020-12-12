package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.DirectorDto;
import com.cinemaster.backend.data.entity.Director;
import com.cinemaster.backend.data.service.DirectorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DirectorServiceTest {

    @Autowired
    DirectorService directorService;

    @Test
    public void testSaveDirectorAndCheckFindAll() {
        Director director = new Director();
        director.setName("Ciccio Boragina");
        directorService.save(director);

        List<DirectorDto> dto = directorService.findAll();
        Assert.assertEquals(1, dto.size());
    }
}
