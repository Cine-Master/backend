package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.AccountDto;
import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<AccountPasswordLessDto> checkCredentials(String username, String hashedPassword);

    void save(AccountDto accountDto);

}
