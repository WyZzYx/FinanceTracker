package com.example.financetracker.services;

import com.example.financetracker.models.Account;
import com.example.financetracker.models.ApplicationUser;
import com.example.financetracker.DTO.LoginResponseDTO;
import com.example.financetracker.models.Role;
import com.example.financetracker.repository.AccountRepository;
import com.example.financetracker.repository.RoleRepository;
import com.example.financetracker.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository  userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.accountRepository = accountRepository;
    }

    public ApplicationUser registerUser(String username, String password, String email) {

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        ApplicationUser newUser = userRepository.save(new ApplicationUser(0L, username, encodedPassword, email, roles));
        System.out.println("User created with ID: " + newUser.getId());


        Account newAccount = new Account(userRepository.findByUsername(username).get(), "Default Account", 0);
        accountRepository.save(newAccount);

        return newUser;

    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {

            System.out.println(username + " " + password);
            ApplicationUser user = userRepository.findByUsername(username).orElse(null);


            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.generateJWT(authentication);


            return new LoginResponseDTO(user, token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new LoginResponseDTO(null, " ");
        }
    }

}
