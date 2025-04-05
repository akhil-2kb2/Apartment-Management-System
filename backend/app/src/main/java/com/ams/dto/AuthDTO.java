package com.ams.dto;

import com.ams.enums.Shift; // ✅ Import your enum
import java.util.List;

public class AuthDTO {
    private String username;
    private String password;
    private List<String> roles;
    private boolean isActive;
    private Shift shift; // ✅ Correct type instead of String

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public boolean isIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public Shift getShift() { return shift; }
    public void setShift(Shift shift) { this.shift = shift; }
}
