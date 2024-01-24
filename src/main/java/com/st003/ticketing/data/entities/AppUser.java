package com.st003.ticketing.data.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.st003.ticketing.data.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "APPUSER_ID")
    private Long id;

    // email functions as the username
    @Column(length = 254, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role role;

    // CONSTRUCTORS

    protected AppUser() {}

    public AppUser(String email) {
        this.email = email;
    }

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String plaintextPassword) {
        BCryptPasswordEncoder bycrypt = new BCryptPasswordEncoder();
        this.passwordHash = bycrypt.encode(plaintextPassword);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUser(email=" + email + ")";
    }
}
