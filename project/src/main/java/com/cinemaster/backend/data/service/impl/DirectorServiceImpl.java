package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.DirectorDao;
import com.cinemaster.backend.data.dto.DirectorDto;
import com.cinemaster.backend.data.entity.Director;
import com.cinemaster.backend.data.service.DirectorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorDao directorDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(DirectorDto directorDto) {
        Director director = modelMapper.map(directorDto, Director.class);
        directorDao.saveAndFlush(director);
        directorDto.setId(director.getId());
    }

    @Override
    public void update(DirectorDto directorDto) {
        Director director = modelMapper.map(directorDto, Director.class);
        directorDao.saveAndFlush(director);
    }

    @Override
    public void delete(DirectorDto directorDto) {
        Director director = modelMapper.map(directorDto, Director.class);
        directorDao.delete(director);
    }

    @Override
    public Optional<DirectorDto> findById(Long id) {
        Optional<Director> optional = directorDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(director -> modelMapper.map(director, DirectorDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<DirectorDto> findAll() {
        return directorDao.findAll().stream().map(director -> modelMapper.map(director, DirectorDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<DirectorDto> findAllByNameContains(String name) {
        return directorDao.findAllByNameContains(name).stream().map(director -> modelMapper.map(director, DirectorDto.class)).collect(Collectors.toList());
    }
}
