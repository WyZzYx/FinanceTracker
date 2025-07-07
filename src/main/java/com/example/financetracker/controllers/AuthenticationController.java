package com.example.financetracker.controllers;

import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.DTO.LoginResponseDTO;
import com.example.financetracker.DTO.RegistrationDTO;
import com.example.financetracker.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmail());
    }
    @GetMapping("/register")
    public String returnRegisterPage() {
        return "register";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody RegistrationDTO body) {
        LoginResponseDTO response = authenticationService.loginUser(body.getUsername(), body.getPassword());

        if (response.getJwtToken() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "Authorization") // Ensure frontend can read the header
                .header("Authorization", "Bearer " + response.getJwtToken()) // Send token in header
                .body(response);
    }


}


