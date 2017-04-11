package com.github.lhsm.service;

import com.github.lhsm.dao.entity.Account;
import com.github.lhsm.dao.AccountRepository;
import com.github.lhsm.service.to.AccountInfo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl subj;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    public void create() throws Exception {
        final String id = "1";

        Mockito.when(accountRepository.exists(id)).thenReturn(false);

        AccountInfo info = subj.create("1");

        verify(accountRepository).save(any(Account.class));
        verify(passwordEncoder).encode(anyString());

        Assert.assertThat(info.isSuccess(), CoreMatchers.is(true));
    }

    @Test
    public void create_AccountExists() throws Exception {
        final String id = "1";

        Mockito.when(accountRepository.exists(id)).thenReturn(true);

        AccountInfo info = subj.create("1");

        verify(accountRepository, never()).save(any(Account.class));

        Assert.assertThat(info.isSuccess(), CoreMatchers.is(false));
    }

}