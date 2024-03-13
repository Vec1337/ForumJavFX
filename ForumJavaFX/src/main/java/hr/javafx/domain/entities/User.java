package hr.javafx.domain.entities;

import hr.javafx.domain.enums.UserRole;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User extends Entity implements Serializable {

    private String username;
    private String passwordHash;
    private UserRole role;


    public User(Integer userID, String username, String passwordHash, UserRole role) {
        super(userID);
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
