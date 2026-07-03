package com.librarymanagement.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a user role in the Library Management System.
 *
 * <p>Examples:
 * <ul>
 *     <li>ADMIN</li>
 *     <li>LIBRARIAN</li>
 * </ul>
 *
 * This entity maps to the {@code roles} table in the database.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class Role {

    /**
     * Unique identifier of the role.
     */
    private Integer roleId;

    /**
     * Name of the role.
     */
    private String roleName;

    /**
     * Description of the role.
     */
    private String description;

    /**
     * Record creation timestamp.
     */
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public Role() {
    }

    /**
     * Parameterized constructor.
     *
     * @param roleId      role ID
     * @param roleName    role name
     * @param description role description
     * @param createdAt   creation timestamp
     */
    public Role(Integer roleId,
                String roleName,
                String description,
                LocalDateTime createdAt) {

        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Role role)) {
            return false;
        }

        return Objects.equals(roleId, role.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}