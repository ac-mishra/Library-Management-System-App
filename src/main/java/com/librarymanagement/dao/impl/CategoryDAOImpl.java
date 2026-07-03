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

        throw new UnsupportedOperationException();

    }

    @Override
    public boolean update(Category category) {

        throw new UnsupportedOperationException();

    }

    @Override
    public boolean delete(Integer id) {

        throw new UnsupportedOperationException();

    }
}