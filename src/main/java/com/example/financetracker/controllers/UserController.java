package com.example.financetracker.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/")
    public String PrintWord() {

        return "User level access";
    }


}
