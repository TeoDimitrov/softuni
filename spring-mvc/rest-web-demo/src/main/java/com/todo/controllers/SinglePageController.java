package com.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SinglePageController {

    @GetMapping("/")
    public String getSinglePage(){
        return "home";
    }
}
