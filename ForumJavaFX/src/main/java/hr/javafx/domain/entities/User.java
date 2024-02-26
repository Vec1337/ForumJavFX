package hr.javafx.domain.entities;

import hr.javafx.domain.enums.UserRole;

public class User {

    private Integer userID;
    private String username;
    private String passwordHash;
    private UserRole role;

    public User(Integer userID, String username, String passwordHash, UserRole role) {
        this.userID = userID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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
}
