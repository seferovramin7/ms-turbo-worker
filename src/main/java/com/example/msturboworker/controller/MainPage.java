package com.example.msturboworker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {
    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/privacy-policy")
    public String privacyPolicy(Model model) {
        return "privacy-policy";
    }
}
