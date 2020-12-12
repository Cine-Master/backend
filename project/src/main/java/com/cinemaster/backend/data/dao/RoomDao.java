package com.cinemaster.backend.data.dao;

import com.cinemaster.backend.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomDao extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
}
