package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.AdminDto;
import com.cinemaster.backend.data.entity.Admin;
import com.cinemaster.backend.data.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @Test
    public void testAdminService() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setHashedPassword("admin");
        adminService.save(admin);

        Optional<AdminDto> dto = adminService.checkAdminCredentials("admin", "admin");
        if (dto.isPresent()) {
            assert true;
        } else {
            assert false;
        }
    }
}
