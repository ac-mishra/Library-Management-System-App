package com.librarymanagement.dao.impl;

import com.librarymanagement.common.constants.CategoryQueries;
import com.librarymanagement.config.DBConnection;
import com.librarymanagement.entity.Category;
import com.librarymanagement.mapper.CategoryRowMapper;
import com.librarymanagement.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl
        implements CategoryRepository {

    @Override
    public List<Category> findAll() {

        List<Category> list =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                CategoryQueries.FIND_ALL
                        );

                ResultSet rs =
                        ps.executeQuery()

        ) {

            while (rs.next()) {

                list.add(
                        CategoryRowMapper.map(rs)
                );

            }

        } catch (SQLException exception) {

            throw new RuntimeException(
                    exception
            );

        }

        return list;

    }

    @Override
    public Optional<Category> findById(Integer id) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                CategoryQueries.FIND_BY_ID
                        )

        ) {

            ps.setInt(1, id);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return Optional.of(
                            CategoryRowMapper.map(rs)
                    );

                }

            }

        } catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

    @Override
    public Optional<Category> findByName(String name) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                CategoryQueries.FIND_BY_NAME
                        )

        ) {

            ps.setString(1, name);

            try (ResultSet rs =
                         ps.executeQuery()) {

                if (rs.next()) {

                    return Optional.of(
                            CategoryRowMapper.map(rs)
                    );

                }

            }

        } catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

    @Override
    public Category save(Category category) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                CategoryQueries.INSERT,
                                PreparedStatement.RETURN_GENERATED_KEYS
                        )

        ) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {

                    category.setCategoryId(rs.getInt(1));

                }

            }

            return category;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public boolean update(Category category) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                CategoryQueries.UPDATE
                        )

        ) {

            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategoryId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public boolean delete(Integer id) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                CategoryQueries.DELETE
                        )

        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }
}