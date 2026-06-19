package com.example.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "GTLIA20900 - Asset Management System");
        return "index";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("title", "Main Menu");
        return "menu";
    }
}