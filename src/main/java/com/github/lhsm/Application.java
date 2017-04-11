package com.github.lhsm;

import com.github.lhsm.dao.AccountRepository;
import com.github.lhsm.dao.RedirectRepository;
import com.github.lhsm.dao.StatisticRepository;
import com.github.lhsm.util.Shortener;
import com.github.lhsm.service.AccountService;
import com.github.lhsm.service.AccountServiceImpl;
import com.github.lhsm.service.AccountUserDetails;
import com.github.lhsm.service.RedirectService;
import com.github.lhsm.service.RedirectServiceImpl;
import com.github.lhsm.service.StatisticService;
import com.github.lhsm.service.StatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    @Autowired
    private RedirectRepository redirectRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AccountUserDetails(accountRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    public Shortener shortener() {
        return new Shortener(ALPHABET);
    }

    @Bean
    public RedirectService redirectService() {
        return new RedirectServiceImpl(redirectRepository, accountRepository, statisticRepository, shortener());
    }

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl(accountRepository, passwordEncoder());
    }

    @Bean
    public StatisticService statisticService() {
        return new StatisticServiceImpl(statisticRepository);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
