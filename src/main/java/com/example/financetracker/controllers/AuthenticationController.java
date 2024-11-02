package com.example.financetracker.controllers;

import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.models.LoginResponseDTO;
import com.example.financetracker.models.RegistrationDTO;
import com.example.financetracker.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {


    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerUser(@RequestBody RegistrationDTO body) {
        try {
            ApplicationUser user = authenticationService.registerUser(body.getUsername(), body.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody RegistrationDTO body) {
        LoginResponseDTO response = authenticationService.loginUser(body.getUsername(), body.getPassword());
        if (response.getUser() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
