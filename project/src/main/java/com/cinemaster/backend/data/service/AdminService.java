package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.AdminDto;
import com.cinemaster.backend.data.entity.Admin;

import java.util.Optional;

public interface AdminService {

    Optional<AdminDto> checkAdminCredentials(String username, String hashedPassword);

    void save(Admin admin);

}
