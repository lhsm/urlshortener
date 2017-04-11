package com.github.lhsm.service;

import com.github.lhsm.dao.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class AccountUserDetails implements UserDetailsService {

    private final CrudRepository<Account, String> repository;

    public AccountUserDetails(CrudRepository<Account, String> repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account entity = repository.findOne(username);

        return new User(entity.getId(), entity.getPassword(), Collections.emptyList());
    }

}
