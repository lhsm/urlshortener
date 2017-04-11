package com.github.lhsm.web;

import com.github.lhsm.dao.entity.Redirect;
import com.github.lhsm.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class RedirectController {

    @Autowired
    private RedirectService redirectService;

    @RequestMapping(path = "/{path}")
    public ModelAndView redirect(@PathVariable(value = "path") String path) {
        Optional<Redirect> redirect = redirectService.redirect(path);

        ModelAndView modelAndView = new ModelAndView();

        if (redirect.isPresent()) {
            RedirectView view = new RedirectView();
            view.setStatusCode(redirect.get().getType());
            view.setUrl(redirect.get().getUrl());
            modelAndView.setView(view);
        } else {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        }

        return modelAndView;
    }


}
