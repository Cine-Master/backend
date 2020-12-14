package com.cinemaster.backend.data.service;

import com.cinemaster.backend.data.dto.AccountDto;
import com.cinemaster.backend.data.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<AccountDto> checkCredentials(String username, String hashedPassword);

    void save(Account account);

}
