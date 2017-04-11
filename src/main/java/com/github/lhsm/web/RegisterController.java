package com.github.lhsm.web;

import com.github.lhsm.service.RedirectService;
import com.github.lhsm.service.to.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping(path = "/register")
public class RegisterController {

    @Autowired
    private RedirectService redirectService;

    @RequestMapping(method = RequestMethod.POST)
    public RegisterInfo register(@Valid @RequestBody RegisterRequest registerInfo, Principal principal) {
        return redirectService.register(principal.getName(), registerInfo.url, registerInfo.redirectType);
    }

    public static class RegisterRequest {

        @NotNull
        private String url;

        private Integer redirectType;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setRedirectType(Integer redirectType) {
            this.redirectType = redirectType;
        }
    }

}
