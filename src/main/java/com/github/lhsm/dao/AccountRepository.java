package com.github.lhsm.dao;

import com.github.lhsm.dao.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, String> {

}
