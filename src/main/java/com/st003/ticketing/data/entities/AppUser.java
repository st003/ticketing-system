package com.st003.ticketing.data.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email functions as the username
    @Column(length = 254, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String passwordHash;

    protected AppUser() {}

    public AppUser(String email, String plaintextPassword) {
        this.email = email;
        BCryptPasswordEncoder bycrypt = new BCryptPasswordEncoder();
        this.passwordHash = bycrypt.encode(plaintextPassword);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "AppUser(email=" + email + ")";
    }
}
