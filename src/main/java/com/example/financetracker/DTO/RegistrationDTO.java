package com.example.financetracker.DTO;


public class RegistrationDTO {
    private String username;
    private String password;
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
