package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.ActorDto;
import com.cinemaster.backend.data.service.ActorService;
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
public class ActorServiceTest {

    @Autowired
    ActorService actorService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testActorService() {
        ActorDto actorDto = new ActorDto();
        actorDto.setName("Ciccio Riccio");
        actorService.save(actorDto);

        List<ActorDto> dto = actorService.findAll();
        Assert.assertEquals(1, dto.size());

        actorService.delete(actorDto);

        dto = actorService.findAll();
        Assert.assertEquals(0, dto.size());

        actorDto = new ActorDto();
        actorDto.setName("Ciccio Riccio");
        actorService.save(actorDto);

        List<ActorDto> actors = actorService.findAllByNameContains("Ciccio");
        actorDto = actors.get(0);
        actorDto.setName("Francesco Riccio");
        actorService.update(actorDto);

        actors = actorService.findAllByNameContains("Ciccio");
        Assert.assertEquals(0, actors.size());

        actors = actorService.findAllByNameContains("Francesco");
        Assert.assertEquals(1, actors.size());

        actorService.delete(actorDto);
    }
}
