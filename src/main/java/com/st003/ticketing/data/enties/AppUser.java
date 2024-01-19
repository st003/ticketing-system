package com.st003.ticketing.data.enties;

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

    @Column(length = 254, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String passwordHash;

    protected AppUser() {}

    public AppUser(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    // email functions as the username
    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void hashPlaintextPassword(String plaintextPassword) {
        BCryptPasswordEncoder bycrypt = new BCryptPasswordEncoder();
        this.passwordHash = bycrypt.encode(plaintextPassword);
    }

    @Override
    public String toString() {
        return "AppUser(email=" + email + ")";
    }
}
