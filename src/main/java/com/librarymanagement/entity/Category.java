package com.librarymanagement.entity;

import java.time.LocalDateTime;

/**
 * Category Entity.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class Category {

    private Integer categoryId;
    private String categoryName;
    private String description;
    private LocalDateTime createdAt;

    public Category() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

}