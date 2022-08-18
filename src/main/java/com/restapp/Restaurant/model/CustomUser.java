package com.restapp.Restaurant.model;

import javax.persistence.*;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userPassword;
    @ManyToOne
    @JoinColumn(name = "user_role")
    private CustomRole userRole;

    public CustomUser() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public CustomRole getUserRole() {
        return userRole;
    }

    public void setUserRole(CustomRole userRole) {
        this.userRole = userRole;
    }
}
