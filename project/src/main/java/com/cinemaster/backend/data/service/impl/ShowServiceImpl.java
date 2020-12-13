package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.CategoryDao;
import com.cinemaster.backend.data.dao.ShowDao;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.entity.Show;
import com.cinemaster.backend.data.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowDao showDao;

    @Autowired
    private CategoryDao categoryDao;

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
    public Optional<ShowDto> findById(Long id) {
        Optional<Show> optional = showDao.findById(id);
        if (optional.isPresent()) {
            return optional.map(show -> modelMapper.map(show, ShowDto.class));
        }
        return Optional.empty();
    }

    @Override
    public List<ShowDto> findAll() {
        return showDao.findAll().stream().map(show -> modelMapper.map(show, ShowDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ShowDto> findAllByNameContains(String name) {
        return showDao.findAllByNameContains(name).stream().map(show -> modelMapper.map(show, ShowDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ShowDto> findAllByCategoriesNames(String... categories) {
        List<ShowDto> dto = new ArrayList<>();

        List<Show> shows = showDao.findAll();
        shows.forEach(show -> show.getCategories().forEach(category -> {
            if (Arrays.stream(categories).anyMatch(category.getName()::contains)) {
                dto.add(modelMapper.map(show, ShowDto.class));
                return;
            }
        }));

        return dto;
    }
}
