package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.RoomDto;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.service.RoomService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Test
    public void testSaveRoomAndCheckFindAll() {
        Room room = new Room();
        room.setName("Sala 1");
        roomService.save(room);

        List<RoomDto> dto = roomService.findAll();
        Assert.assertEquals(1, dto.size());
    }
}
