package com.example.financetracker.models;


public class RegistrationDTO {
    private String username;
    private String password;

    public RegistrationDTO(String username)
    {
        this.username = username;
    }

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public RegistrationDTO() {
        super();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
