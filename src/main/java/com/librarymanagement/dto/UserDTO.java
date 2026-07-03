package com.librarymanagement.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for User.
 *
 * Used to transfer user information between
 * the service layer and the presentation layer.
 *
 * Sensitive information such as passwords is excluded.
 */
public class UserDTO implements Serializable {

    private Integer userId;

    private String fullName;

    private String username;

    private String email;

    private String phone;

    private String roleName;

    private Boolean active;

    public UserDTO() {
    }

    public UserDTO(Integer userId,
                   String fullName,
                   String username,
                   String email,
                   String phone,
                   String roleName,
                   Boolean active) {

        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.roleName = roleName;
        this.active = active;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roleName='" + roleName + '\'' +
                ", active=" + active +
                '}';
    }
}