package com.example.financetracker.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @GetMapping(" ")
    public String PrintWord() {

        return "Admin level access";
    }


}
