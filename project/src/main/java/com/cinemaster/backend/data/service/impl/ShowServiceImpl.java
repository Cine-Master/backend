package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.ShowDao;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.entity.Show;
import com.cinemaster.backend.data.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowDao showDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(Show show) {
        showDao.saveAndFlush(show);
    }

    @Override
    public void update(Show show) {
        showDao.saveAndFlush(show);
    }

    @Override
    public void delete(Show show) {
        showDao.delete(show);
    }

    @Override
    public List<ShowDto> findAll() {
        return showDao.findAll().stream().map(show -> modelMapper.map(show, ShowDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShowDto> findAllByName(String name) {
        return showDao.findAllByName(name).stream().map(show -> modelMapper.map(show, ShowDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ShowDto> findAllByCategories(String... categories) {
        return showDao.findAllByCategories(categories).stream().map(show -> modelMapper.map(show, ShowDto.class)).collect(Collectors.toList());
    }
}
