package com.nit.plantsproject.ClassGetSet;

public class User {
    private String name;
    private String email;
    private String password;

    public User() {
        // Diperlukan oleh Firebase Realtime Database
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

