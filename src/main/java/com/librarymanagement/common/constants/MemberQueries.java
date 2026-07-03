package com.librarymanagement.common.constants;

public final class MemberQueries {

    private MemberQueries() {
    }

    public static final String INSERT_MEMBER = """
        INSERT INTO members
        (
            member_code,
            first_name,
            last_name,
            gender,
            email,
            phone,
            address,
            date_of_birth,
            join_date,
            membership_status
        )
        VALUES
        (?,?,?,?,?,?,?,?,?,?)
        """;

    public static final String UPDATE_MEMBER = """
        UPDATE members
        SET
            member_code=?,
            first_name=?,
            last_name=?,
            gender=?,
            email=?,
            phone=?,
            address=?,
            date_of_birth=?,
            join_date=?,
            membership_status=?
        WHERE member_id=?
        """;

    public static final String DELETE_MEMBER = """
        DELETE FROM members
        WHERE member_id=?
        """;

    public static final String FIND_BY_ID = """
        SELECT *
        FROM members
        WHERE member_id=?
        """;

    public static final String FIND_ALL = """
        SELECT *
        FROM members
        ORDER BY member_id DESC
        """;

    public static final String FIND_BY_MEMBER_CODE = """
        SELECT *
        FROM members
        WHERE member_code=?
        """;

    public static final String FIND_BY_EMAIL = """
        SELECT *
        FROM members
        WHERE email=?
        """;
}