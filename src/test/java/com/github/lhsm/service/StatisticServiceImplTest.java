package com.github.lhsm.service;

import com.github.lhsm.dao.StatisticRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceImplTest {

    @InjectMocks
    private StatisticServiceImpl subj;

    @Mock
    private StatisticRepository statisticRepository;

    @Test
    public void findAll() throws Exception {
        final String accountId = "accountId";

        subj.findAll(accountId);

        verify(statisticRepository).findAllByAccount(accountId);
    }

}