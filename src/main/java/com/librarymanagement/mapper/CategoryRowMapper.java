package com.librarymanagement.mapper;

import com.librarymanagement.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public final class CategoryRowMapper {

    private CategoryRowMapper() {
    }

    public static Category map(ResultSet rs)
            throws SQLException {

        Category category =
                new Category();

        category.setCategoryId(
                rs.getInt("category_id")
        );

        category.setCategoryName(
                rs.getString("category_name")
        );

        category.setDescription(
                rs.getString("description")
        );

        Timestamp created =
                rs.getTimestamp("created_at");

        if (created != null) {

            category.setCreatedAt(
                    created.toLocalDateTime()
            );

        }

        return category;

    }

}