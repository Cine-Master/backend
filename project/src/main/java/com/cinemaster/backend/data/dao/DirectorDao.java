package com.cinemaster.backend.data.dao;

import com.cinemaster.backend.data.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DirectorDao extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
}
