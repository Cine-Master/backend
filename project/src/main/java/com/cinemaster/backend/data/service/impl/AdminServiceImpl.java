package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.AdminDao;
import com.cinemaster.backend.data.dto.AdminDto;
import com.cinemaster.backend.data.entity.Admin;
import com.cinemaster.backend.data.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<AdminDto> checkAdminCredentials(String username, String hashedPassword) {
        Optional<Admin> optional = adminDao.findByUsernameAndHashedPassword(username, hashedPassword);
        if (optional.isPresent()) {
            return optional.map(admin -> modelMapper.map(admin, AdminDto.class));
        }
        return Optional.empty();
    }

    @Override
    public void save(Admin admin) {
        adminDao.saveAndFlush(admin);
    }
}
