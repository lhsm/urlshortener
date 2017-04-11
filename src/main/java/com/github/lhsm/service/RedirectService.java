package com.github.lhsm.service;

import com.github.lhsm.dao.entity.Redirect;
import com.github.lhsm.service.to.RegisterInfo;

import java.util.Optional;

public interface RedirectService {

    RegisterInfo register(String accountId, String url, Integer redirectStatus);

    Optional<Redirect> redirect(String hash);

}
