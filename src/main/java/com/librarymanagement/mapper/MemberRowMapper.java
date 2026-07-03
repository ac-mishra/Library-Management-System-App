package com.librarymanagement.mapper;

import com.librarymanagement.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Maps ResultSet to Member.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public final class MemberRowMapper {

    private MemberRowMapper() {
    }

    public static Member map(ResultSet resultSet)
            throws SQLException {

        Member member = new Member();

        member.setMemberId(
                resultSet.getInt("member_id")
        );

        member.setMemberCode(
                resultSet.getString("member_code")
        );

        member.setFirstName(
                resultSet.getString("first_name")
        );

        member.setLastName(
                resultSet.getString("last_name")
        );

        member.setGender(
                resultSet.getString("gender")
        );

        member.setEmail(
                resultSet.getString("email")
        );

        member.setPhone(
                resultSet.getString("phone")
        );

        member.setAddress(
                resultSet.getString("address")
        );

        if (resultSet.getDate("date_of_birth") != null) {

            member.setDateOfBirth(
                    resultSet.getDate("date_of_birth")
                            .toLocalDate()
            );

        }

        if (resultSet.getDate("join_date") != null) {

            member.setJoinDate(
                    resultSet.getDate("join_date")
                            .toLocalDate()
            );

        }

        member.setMembershipStatus(
                resultSet.getString("membership_status")
        );

        Timestamp created =
                resultSet.getTimestamp("created_at");

        if (created != null) {

            member.setCreatedAt(
                    created.toLocalDateTime()
            );

        }

        Timestamp updated =
                resultSet.getTimestamp("updated_at");

        if (updated != null) {

            member.setUpdatedAt(
                    updated.toLocalDateTime()
            );

        }

        return member;

    }

}