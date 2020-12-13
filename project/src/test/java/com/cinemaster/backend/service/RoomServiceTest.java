package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.service.RoomService;
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
public class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testRoomService() {
        Room room = new Room();
        room.setName("Sala 1");
        roomService.save(room);

        List<RoomDto> dto = roomService.findAll();
        Assert.assertEquals(1, dto.size());

        roomService.delete(room);

        room = new Room();
        room.setName("Sala 1");
        roomService.save(room);

        List<Room> rooms = roomService.findAllByNameContains("1").stream().map(roomDto -> modelMapper.map(roomDto, Room.class)).collect(Collectors.toList());
        room = rooms.get(0);
        room.setName("Sala 2");
        roomService.update(room);

        rooms = roomService.findAllByNameContains("1").stream().map(roomDto -> modelMapper.map(roomDto, Room.class)).collect(Collectors.toList());
        Assert.assertEquals(0, rooms.size());

        rooms = roomService.findAllByNameContains("2").stream().map(roomDto -> modelMapper.map(roomDto, Room.class)).collect(Collectors.toList());
        Assert.assertEquals(1, rooms.size());

        roomService.delete(room);
    }
}
