package com.github.lhsm.service;

import com.github.lhsm.dao.AccountRepository;
import com.github.lhsm.dao.RedirectRepository;
import com.github.lhsm.dao.StatisticRepository;
import com.github.lhsm.dao.entity.Redirect;
import com.github.lhsm.dao.entity.Statistic;
import com.github.lhsm.service.to.ImmutableRegisterInfo;
import com.github.lhsm.service.to.RegisterInfo;
import com.github.lhsm.util.Shortener;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;

public class RedirectServiceImpl implements RedirectService {

    private final EnumSet<HttpStatus> REDIRECT_STATUSES = EnumSet.of(HttpStatus.MOVED_PERMANENTLY, HttpStatus.FOUND);

    private final RedirectRepository repository;

    private final AccountRepository accountRepository;

    private final StatisticRepository statisticRepository;

    private final Shortener shortener;

    public RedirectServiceImpl(RedirectRepository repository, AccountRepository accountRepository, StatisticRepository statisticRepository, Shortener shortener) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.statisticRepository = statisticRepository;
        this.shortener = shortener;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterInfo register(String accountId, String url, Integer redirectStatus) {
        Redirect redirect = repository.findByAccountAndUrl(accountId, url);

        if (redirect == null) {
            redirect = repository.save(new Redirect(accountRepository.findOne(accountId), url, getRedirectStatus(redirectStatus)));

            statisticRepository.save(new Statistic(redirect));
        }

        return ImmutableRegisterInfo.builder()
                .shortUrl(shortener.encode(redirect.getId()))
                .build();
    }

    @Override
    public Optional<Redirect> redirect(String hash) {
        Optional<Long> id = shortener.decode(hash);

        Optional<Redirect> redirect = id.map(repository::findOne);

        if (redirect.isPresent()) {
            statisticRepository.incrementByRedirectId(redirect.get().getId());//TODO async
            return redirect;
        }

        return Optional.empty();
    }

    protected HttpStatus getRedirectStatus(Integer redirectStatus) {
        return REDIRECT_STATUSES.stream()
                .filter(_status -> Objects.equals(_status.value(), redirectStatus))
                .findFirst().orElse(HttpStatus.FOUND);
    }

}
