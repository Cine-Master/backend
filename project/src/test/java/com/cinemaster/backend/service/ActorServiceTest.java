package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.ActorDto;
import com.cinemaster.backend.data.entity.Actor;
import com.cinemaster.backend.data.service.ActorService;
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
public class ActorServiceTest {

    @Autowired
    ActorService actorService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testActorService() {
        Actor actor = new Actor();
        actor.setName("Ciccio Riccio");
        actorService.save(actor);

        List<ActorDto> dto = actorService.findAll();
        Assert.assertEquals(1, dto.size());

        actorService.delete(actor);

        actor = new Actor();
        actor.setName("Ciccio Riccio");
        actorService.save(actor);

        List<Actor> actors = actorService.findAllByNameContains("Ciccio").stream().map(actorDto -> modelMapper.map(actorDto, Actor.class)).collect(Collectors.toList());
        actor = actors.get(0);
        actor.setName("Francesco Riccio");
        actorService.update(actor);

        actors = actorService.findAllByNameContains("Ciccio").stream().map(actorDto -> modelMapper.map(actorDto, Actor.class)).collect(Collectors.toList());
        Assert.assertEquals(0, actors.size());

        actors = actorService.findAllByNameContains("Francesco").stream().map(actorDto -> modelMapper.map(actorDto, Actor.class)).collect(Collectors.toList());
        Assert.assertEquals(1, actors.size());

        actorService.delete(actor);
    }
}
