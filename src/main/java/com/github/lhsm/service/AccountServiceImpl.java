package com.github.lhsm.service;

import com.github.lhsm.dao.AccountRepository;
import com.github.lhsm.dao.entity.Account;
import com.github.lhsm.service.to.AccountInfo;
import com.github.lhsm.service.to.ImmutableAccountInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountInfo create(String accountId) {
        if (accountRepository.exists(accountId)) {
            return ImmutableAccountInfo.builder()
                    .description("Account already exists")
                    .isSuccess(false)
                    .build();
        }

        String password = buildPassword();

        accountRepository.save(new Account(accountId, passwordEncoder.encode(password)));

        return ImmutableAccountInfo.builder()
                .password(password)
                .description("Your account is opened")
                .isSuccess(true)
                .build();
    }

    private String buildPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

}
