package com.cinemaster.backend.data.dao;

import com.cinemaster.backend.data.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventDao extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

}
