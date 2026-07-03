package com.librarymanagement.dao.impl;

import com.librarymanagement.config.DBConnection;
import com.librarymanagement.common.constants.MemberQueries;
import com.librarymanagement.entity.Member;
import com.librarymanagement.mapper.MemberRowMapper;
import com.librarymanagement.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

/**
 * Member DAO Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class MemberDAOImpl
        implements MemberRepository {

    @Override
    public Member save(Member member) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                MemberQueries.INSERT_MEMBER,
                                Statement.RETURN_GENERATED_KEYS
                        )

        ) {

            preparedStatement.setString(1,
                    member.getMemberCode());

            preparedStatement.setString(2,
                    member.getFirstName());

            preparedStatement.setString(3,
                    member.getLastName());

            preparedStatement.setString(4,
                    member.getGender());

            preparedStatement.setString(5,
                    member.getEmail());

            preparedStatement.setString(6,
                    member.getPhone());

            preparedStatement.setString(7,
                    member.getAddress());

            preparedStatement.setDate(8,
                    Date.valueOf(
                            member.getDateOfBirth()
                    ));

            preparedStatement.setDate(9,
                    Date.valueOf(
                            member.getJoinDate()
                    ));

            preparedStatement.setString(10,
                    member.getMembershipStatus());

            int rows =
                    preparedStatement.executeUpdate();

            if (rows > 0) {

                ResultSet keys =
                        preparedStatement.getGeneratedKeys();

                if (keys.next()) {

                    member.setMemberId(
                            keys.getInt(1)
                    );

                }

            }

            return member;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }
    @Override
    public boolean update(Member member) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                MemberQueries.UPDATE_MEMBER
                        )

        ) {

            preparedStatement.setString(1,
                    member.getMemberCode());

            preparedStatement.setString(2,
                    member.getFirstName());

            preparedStatement.setString(3,
                    member.getLastName());

            preparedStatement.setString(4,
                    member.getGender());

            preparedStatement.setString(5,
                    member.getEmail());

            preparedStatement.setString(6,
                    member.getPhone());

            preparedStatement.setString(7,
                    member.getAddress());

            preparedStatement.setDate(8,
                    Date.valueOf(
                            member.getDateOfBirth()
                    ));

            preparedStatement.setDate(9,
                    Date.valueOf(
                            member.getJoinDate()
                    ));

            preparedStatement.setString(10,
                    member.getMembershipStatus());

            preparedStatement.setInt(11,
                    member.getMemberId());

            return preparedStatement.executeUpdate() > 0;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }

    @Override
    public boolean delete(Integer id) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                MemberQueries.DELETE_MEMBER
                        )

        ) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

    }

    @Override
    public Optional<Member> findById(Integer id) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                MemberQueries.FIND_BY_ID
                        )

        ) {

            preparedStatement.setInt(1, id);

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return Optional.of(
                            MemberRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

    @Override
    public List<Member> findAll() {

        List<Member> members =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                MemberQueries.FIND_ALL
                        );

                ResultSet resultSet =
                        preparedStatement.executeQuery()

        ) {

            while (resultSet.next()) {

                members.add(
                        MemberRowMapper.map(resultSet)
                );

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return members;

    }

    @Override
    public Optional<Member> findByMemberCode(
            String memberCode) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                MemberQueries.FIND_BY_MEMBER_CODE
                        )

        ) {

            preparedStatement.setString(
                    1,
                    memberCode
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return Optional.of(
                            MemberRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

    @Override
    public Optional<Member> findByEmail(
            String email) {

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                MemberQueries.FIND_BY_EMAIL
                        )

        ) {

            preparedStatement.setString(
                    1,
                    email
            );

            try (

                    ResultSet resultSet =
                            preparedStatement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return Optional.of(
                            MemberRowMapper.map(resultSet)
                    );

                }

            }

        }

        catch (SQLException exception) {

            throw new RuntimeException(exception);

        }

        return Optional.empty();

    }

    @Override
    public boolean existsByMemberCode(
            String memberCode) {

        return findByMemberCode(
                memberCode
        ).isPresent();

    }

}