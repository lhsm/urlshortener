package com.github.lhsm.dao;

import com.github.lhsm.dao.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;


public interface StatisticRepository extends JpaRepository<Statistic, String> {

    @Query(value = "select s from Statistic s WHERE s.redirect.account.id = ?1")
    Collection<Statistic> findAllByAccount(String accountId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Statistic s SET s.count = s.count + 1 WHERE s.redirect.id = ?1")
    int incrementByRedirectId(Long id);

}
