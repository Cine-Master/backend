package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.DirectorDto;
import com.cinemaster.backend.data.service.DirectorService;
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
public class DirectorServiceTest {

    @Autowired
    DirectorService directorService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testDirectorService() {
        DirectorDto directorDto = new DirectorDto();
        directorDto.setName("Ciccio Boragina");
        directorService.save(directorDto);

        List<DirectorDto> dto = directorService.findAll();
        Assert.assertEquals(1, dto.size());

        directorService.delete(directorDto);

        directorDto = new DirectorDto();
        directorDto.setName("Ciccio Boragina");
        directorService.save(directorDto);

        List<DirectorDto> directors = directorService.findAllByNameContains("Ciccio");
        directorDto = directors.get(0);
        directorDto.setName("Francesco Boragina");
        directorService.update(directorDto);

        directors = directorService.findAllByNameContains("Ciccio");
        Assert.assertEquals(0, directors.size());

        directors = directorService.findAllByNameContains("Francesco");
        Assert.assertEquals(1, directors.size());

        directorService.delete(directorDto);
    }
}
