package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.DirectorDao;
import com.cinemaster.backend.data.dto.DirectorDto;
import com.cinemaster.backend.data.entity.Director;
import com.cinemaster.backend.data.service.DirectorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorDao directorDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(Director director) {
        directorDao.saveAndFlush(director);
    }

    @Override
    public void update(Director director) {
        directorDao.saveAndFlush(director);
    }

    @Override
    public void delete(Director director) {
        directorDao.delete(director);
    }

    @Override
    public List<DirectorDto> findAll() {
        return directorDao.findAll().stream().map(director -> modelMapper.map(director, DirectorDto.class)).collect(Collectors.toList());
    }
}
