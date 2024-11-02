package com.example.financetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/")
    public String PrintWord() {

        return "register.html";
    }


}
