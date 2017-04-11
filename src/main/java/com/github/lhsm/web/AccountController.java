package com.github.lhsm.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lhsm.service.AccountService;
import com.github.lhsm.service.to.AccountInfo;
import com.github.lhsm.service.to.ImmutableAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = {"/account"})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    public AccountInfo account(@Valid @RequestBody AccountId accountId) {
        return accountService.create(accountId.accountId);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public AccountInfo error() {
        return ImmutableAccountInfo.builder()
                .isSuccess(false)
                .description("Internal server error")
                .build();
    }

    public static class AccountId {

        @NotNull
        private String accountId;

        @JsonProperty("AccountId")
        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }
    }

}
