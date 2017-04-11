package com.github.lhsm.web;

import com.github.lhsm.service.to.StatisticInfo;
import com.github.lhsm.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(method = RequestMethod.GET, path = "/{accountId}")
    public Map<String, Long> statistic(@PathVariable(value = "accountId") String accountId) {
        return statisticService.findAll(accountId).stream()
                .collect(Collectors.toMap(StatisticInfo::getUrl, StatisticInfo::getCount));
    }

}
