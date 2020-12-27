package com.cinemaster.backend.data.dao;

import com.cinemaster.backend.data.entity.Event;
import com.cinemaster.backend.data.entity.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventDao extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

}
