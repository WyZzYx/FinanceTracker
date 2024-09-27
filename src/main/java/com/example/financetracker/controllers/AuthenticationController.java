package com.example.financetracker.controllers;

import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.models.LoginResponseDTO;
import com.example.financetracker.models.RegistrationDTO;
import com.example.financetracker.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {

        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}
