package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.service.RoomService;
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
public class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testRoomService() {
        RoomDto roomDto = new RoomDto();
        roomDto.setName("Sala 1");
        roomService.save(roomDto);

        List<RoomDto> dto = roomService.findAll();
        Assert.assertEquals(1, dto.size());

        roomService.delete(roomDto);

        roomDto = new RoomDto();
        roomDto.setName("Sala 1");
        roomService.save(roomDto);

        List<RoomDto> rooms = roomService.findAllByNameContains("1");
        roomDto = rooms.get(0);
        roomDto.setName("Sala 2");
        roomService.update(roomDto);

        rooms = roomService.findAllByNameContains("1");
        Assert.assertEquals(0, rooms.size());

        rooms = roomService.findAllByNameContains("2");
        Assert.assertEquals(1, rooms.size());

        roomService.delete(roomDto);
    }
}
