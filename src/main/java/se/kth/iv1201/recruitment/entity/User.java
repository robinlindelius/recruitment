package se.kth.iv1201.recruitment.entity;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Creates the O/R Mapping for User
 */
@Entity
@Table(name = "users", catalog = "Recruitment")
public class User {

    @Id
    @NotNull
    @Size(min=4, max=20)
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @NotNull
    @Size(min=4, max=20)
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    private Person person;

    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
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

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }
}