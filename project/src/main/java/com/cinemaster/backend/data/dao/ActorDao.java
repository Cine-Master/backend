package com.cinemaster.backend.data.dao;

import com.cinemaster.backend.data.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActorDao extends JpaRepository<Actor, Long>, JpaSpecificationExecutor<Actor> {
}
