package com.cinemaster.backend.data.dao;

import com.cinemaster.backend.data.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShowDao extends JpaRepository<Show, Long>, JpaSpecificationExecutor<Show> {

    List<Show> findAllByName(String name);

    // TODO da controllare
    List<Show> findAllByCategories(String... categories);

}
