package hr.javafx.domain.records;

import hr.javafx.domain.enums.UserRole;

public record Admin(String username, String password, UserRole role) {
}
