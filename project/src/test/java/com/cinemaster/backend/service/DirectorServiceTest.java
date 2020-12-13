package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.DirectorDto;
import com.cinemaster.backend.data.entity.Director;
import com.cinemaster.backend.data.service.DirectorService;
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
public class DirectorServiceTest {

    @Autowired
    DirectorService directorService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testDirectorService() {
        Director director = new Director();
        director.setName("Ciccio Boragina");
        directorService.save(director);

        List<DirectorDto> dto = directorService.findAll();
        Assert.assertEquals(1, dto.size());

        directorService.delete(director);

        director = new Director();
        director.setName("Ciccio Boragina");
        directorService.save(director);

        List<Director> directors = directorService.findAllByNameContains("Ciccio").stream().map(directorDto -> modelMapper.map(directorDto, Director.class)).collect(Collectors.toList());
        director = directors.get(0);
        director.setName("Francesco Boragina");
        directorService.update(director);

        directors = directorService.findAllByNameContains("Ciccio").stream().map(directorDto -> modelMapper.map(directorDto, Director.class)).collect(Collectors.toList());
        Assert.assertEquals(0, directors.size());

        directors = directorService.findAllByNameContains("Francesco").stream().map(directorDto -> modelMapper.map(directorDto, Director.class)).collect(Collectors.toList());
        Assert.assertEquals(1, directors.size());

        directorService.delete(director);
    }
}
