package com.ibm.den.entities.util;

public class LoginResponse {
    private String email;
    public enum UserType {
        LEADER,
        STUDENT,
        MENTOR,
        NOT_FOUND
    }

    private UserType userType;

    public LoginResponse() {
    }

    public LoginResponse(String email, UserType userType) {
        this.email = email;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
