package com.residentevil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }
}
