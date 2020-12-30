package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.CategoryDao;
import com.cinemaster.backend.data.dao.EventDao;
import com.cinemaster.backend.data.dao.ShowDao;
import com.cinemaster.backend.data.dto.ShowDto;
import com.cinemaster.backend.data.entity.Event;
import com.cinemaster.backend.data.entity.Show;
import com.cinemaster.backend.data.service.ShowService;
import com.cinemaster.backend.data.specification.ShowSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowDao showDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(ShowDto showDto) {
        Show show = modelMapper.map(showDto, Show.class);
        showDao.saveAndFlush(show);
        showDto.setId(show.getId());
    }

    @Override
    public void update(ShowDto showDto) {
        Show show = modelMapper.map(showDto, Show.class);
        showDao.saveAndFlush(show);
    }

    @Override
    public void delete(ShowDto showDto) {
        Show show = modelMapper.map(showDto, Show.class);
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
    public List<ShowDto> findAllByFilter(ShowSpecification.Filter filter) {
        return showDao.findAll(ShowSpecification.findAllByFilter(filter)).stream().map(show -> modelMapper.map(show, ShowDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ShowDto> findAllByEventBeforeNextWeek() {
        List<Show> shows = new ArrayList<>();
        for (Event event:eventDao.findAllByDateAfterAndDateBefore(LocalDate.now(), LocalDate.now().plusDays(7))) {
            if (!(shows.contains(event.getShow()))) {
                shows.add(event.getShow());
            }
        }
        return shows.stream().map(show -> modelMapper.map(show, ShowDto.class)).collect(Collectors.toList());
    }
}
