package com.cinemaster.backend.service;

import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.dto.AdminDto;
import com.cinemaster.backend.data.dto.AdminPasswordLessDto;
import com.cinemaster.backend.data.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void testAdminService() {
        AdminDto adminDto = new AdminDto();
        adminDto.setUsername("adminDto");
        adminDto.setHashedPassword("adminDto");
        accountService.save(adminDto);

        Optional<AccountPasswordLessDto> dto = accountService.checkCredentials("adminDto", "adminDto");
        if (dto.isPresent()) {
            Assert.assertTrue(dto.get() instanceof AdminPasswordLessDto);
        } else {
            assert false;
        }
    }
}
