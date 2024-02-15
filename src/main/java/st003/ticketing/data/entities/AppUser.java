package st003.ticketing.data.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import st003.ticketing.data.Role;

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

    @Column(length = 60)
    private String passwordHash;

    @Column(length = 64)
    private String firstName;

    @Column(length = 64)
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role role;

    // CONSTRUCTORS

    public AppUser() {}

    public AppUser(String email) {
        this.email = email;
    }

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Hash a plaintext password set into the passwordHash field
     *
     * @param plaintextPassword
     */
    public void setPassword(String plaintextPassword) {
        BCryptPasswordEncoder bycrypt = new BCryptPasswordEncoder();
        this.passwordHash = bycrypt.encode(plaintextPassword);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUser(email=" + email + " role=" + role + ")";
    }
}
