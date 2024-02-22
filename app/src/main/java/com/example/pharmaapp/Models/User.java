package com.example.pharmaapp.Models;

public class User {
    String email, password, username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String email, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    User() {
    }

    ;
}
