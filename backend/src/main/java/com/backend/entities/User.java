package com.backend.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    private Integer id;
    @NotNull(message = "Email field is required")
    @NotEmpty(message = "Email field is required")
    private String email;
    @NotNull(message = "Password field is required")
    @NotEmpty(message = "Password field is required")
    private String password;
    @NotNull(message = "Name field is required")
    @NotEmpty(message = "Name field is required")
    private String name;
    @NotNull(message = "Surname field is required")
    @NotEmpty(message = "Surname field is required")
    private String surname;
    @NotNull(message = "User type field is required")
    @NotEmpty(message = "User type field is required")
    private String userType;
    @NotNull(message = "Status field is required")
    @NotEmpty(message = "Status field is required")
    private String status;

    public User() {
    }

    public User(Integer id,String email, String password, String name, String surname, String userType, String status) {
        this.id=id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.userType = userType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
