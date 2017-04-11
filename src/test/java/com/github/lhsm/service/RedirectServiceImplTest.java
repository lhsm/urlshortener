package com.github.lhsm.service;

import com.github.lhsm.dao.AccountRepository;
import com.github.lhsm.dao.entity.Redirect;
import com.github.lhsm.dao.RedirectRepository;
import com.github.lhsm.dao.entity.Statistic;
import com.github.lhsm.dao.StatisticRepository;
import com.github.lhsm.util.Shortener;
import com.github.lhsm.service.to.RegisterInfo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RedirectServiceImplTest {

    @InjectMocks
    private RedirectServiceImpl subj;

    @Mock
    private RedirectRepository repository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private StatisticRepository statisticRepository;

    @Mock
    private Shortener shortener;

    @Test
    public void register() throws Exception {
        final String accountId = "accountId";
        final String url = "url";
        final String hash = "hash";
        final Integer redirectType = 301;

        when(repository.findByAccountAndUrl(accountId, url)).thenReturn(null);
        when(shortener.encode(anyInt())).thenReturn(hash);
        when(repository.save(any(Redirect.class))).thenReturn(mock(Redirect.class));

        RegisterInfo registerInfo = subj.register(accountId, url, redirectType);

        verify(repository).save(any(Redirect.class));
        verify(statisticRepository).save(any(Statistic.class));
        verify(accountRepository).findOne(accountId);

        Assert.assertThat(registerInfo.getShortUrl(), CoreMatchers.is(hash));
    }

    @Test
    public void registerExisted() throws Exception {
        final String accountId = "accountId";
        final String url = "url";
        final String hash = "hash";
        final Integer redirectType = 301;

        when(repository.findByAccountAndUrl(accountId, url)).thenReturn(mock(Redirect.class));
        when(shortener.encode(anyInt())).thenReturn(hash);

        RegisterInfo registerInfo = subj.register(accountId, url, redirectType);

        verify(repository, never()).save(any(Redirect.class));
        verify(statisticRepository, never()).save(any(Statistic.class));

        Assert.assertThat(registerInfo.getShortUrl(), CoreMatchers.is(hash));
    }

    @Test
    public void getRedirectStatus() throws Exception {
        final Integer redirectType = 301;

        HttpStatus httpStatus = subj.getRedirectStatus(redirectType);

        Assert.assertThat(httpStatus, CoreMatchers.is(HttpStatus.MOVED_PERMANENTLY));
    }

    @Test
    public void getRedirectStatusEmpty() throws Exception {
        final Integer redirectType = null;

        HttpStatus httpStatus = subj.getRedirectStatus(redirectType);

        Assert.assertThat(httpStatus, CoreMatchers.is(HttpStatus.FOUND));
    }

    @Test
    public void redirect() throws Exception {
    }

}