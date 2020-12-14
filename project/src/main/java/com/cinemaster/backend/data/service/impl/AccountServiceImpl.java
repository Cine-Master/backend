package com.cinemaster.backend.data.service.impl;

import com.cinemaster.backend.data.dao.AccountDao;
import com.cinemaster.backend.data.dto.AccountDto;
import com.cinemaster.backend.data.dto.AccountPasswordLessDto;
import com.cinemaster.backend.data.dto.AdminDto;
import com.cinemaster.backend.data.dto.AdminPasswordLessDto;
import com.cinemaster.backend.data.entity.Account;
import com.cinemaster.backend.data.entity.Admin;
import com.cinemaster.backend.data.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<AccountPasswordLessDto> checkCredentials(String username, String hashedPassword) {
        Optional<Account> optional = accountDao.findByUsernameAndHashedPassword(username, hashedPassword);
        if (optional.isPresent()) {
            return optional.map(account -> {
                if (account instanceof Admin) {
                    return modelMapper.map(account, AdminPasswordLessDto.class);
                } else {
                    return null;
                }
            });
        }
        return Optional.empty();
    }

    @Override
    public void save(AccountDto accountDto) {
        Account account = null;
        if (accountDto instanceof AdminDto) {
            account = modelMapper.map(accountDto, Admin.class);
        }
        accountDao.save(account);
        accountDto.setId(account.getId());
    }
}
