package com.shurankain.planning.app.security.model;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getStringName() {
        return role;
    }
}
