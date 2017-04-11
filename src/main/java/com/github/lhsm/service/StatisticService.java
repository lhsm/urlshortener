package com.github.lhsm.service;

import com.github.lhsm.service.to.StatisticInfo;

import java.util.Collection;

public interface StatisticService {

    Collection<StatisticInfo> findAll(String accountId);

}
