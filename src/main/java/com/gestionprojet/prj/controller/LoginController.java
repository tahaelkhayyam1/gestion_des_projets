package com.gestionprojet.prj.controller;

import com.gestionprojet.prj.entity.Login;
import com.gestionprojet.prj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user, HttpServletRequest request) {
        Login oauthUser = loginService.login(user.getUsername(), user.getPassword());
        if (Objects.nonNull(oauthUser)) {
            request.getSession().setAttribute("loggedInUser", oauthUser);
            return "redirect:/homePage";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/homePage")
    public String homePage(HttpServletRequest request) {
        if (request.getSession().getAttribute("loggedInUser") != null) {
            return "homePage";
        } else {
            return "redirect:/login";
        }
    }

 }
