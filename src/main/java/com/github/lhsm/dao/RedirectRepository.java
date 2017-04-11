package com.github.lhsm.dao;

import com.github.lhsm.dao.entity.Redirect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RedirectRepository extends JpaRepository<Redirect, Long> {

    @Query(value = "select r from Redirect r WHERE r.account.id = ?1 and r.url = ?2")
    Redirect findByAccountAndUrl(String accountId, String url);

}
