package com.github.lhsm.service;

import com.github.lhsm.dao.StatisticRepository;
import com.github.lhsm.service.to.ImmutableStatisticInfo;
import com.github.lhsm.service.to.StatisticInfo;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.EnumSet;
import java.util.stream.Collectors;

public class StatisticServiceImpl implements StatisticService {

    private final EnumSet<HttpStatus> REDIRECT_STATUSES = EnumSet.of(HttpStatus.MOVED_PERMANENTLY, HttpStatus.FOUND);

    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Collection<StatisticInfo> findAll(String accountId) {
        return statisticRepository.findAllByAccount(accountId).stream()
                .map(_statistic -> ImmutableStatisticInfo.builder()
                        .count(_statistic.getCount())
                        .url(_statistic.getRedirect().getUrl())
                        .build())
                .collect(Collectors.toList());
    }

}
