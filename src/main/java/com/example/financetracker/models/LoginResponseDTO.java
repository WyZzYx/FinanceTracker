package com.example.financetracker.models;

public class LoginResponseDTO {
    private ApplicationUser user;
    private String jwtToken;

    public LoginResponseDTO(ApplicationUser user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public LoginResponseDTO() {
        super();
    }

    public ApplicationUser getUser() {
        return this.user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
