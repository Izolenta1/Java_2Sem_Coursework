package com.example.courseWork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @RequestMapping("/")
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/index.html");
        return modelAndView;
    }

    @RequestMapping("/goodCard")
    public ModelAndView getGoodCard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/goodCard.html");
        return modelAndView;
    }

    @RequestMapping("/section")
    public ModelAndView getSection() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/section.html");
        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView getAbout() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/about.html");
        return modelAndView;
    }

    @RequestMapping("/shipnpay")
    public ModelAndView getShipnpay() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/shipnpay.html");
        return modelAndView;
    }

    @RequestMapping("/cart")
    public ModelAndView getCart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/cart.html");
        return modelAndView;
    }
}
