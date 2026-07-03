package com.librarymanagement.service;

import com.librarymanagement.entity.Member;

import java.util.List;
import java.util.Optional;

/**
 * Member Service Interface.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface MemberService {

    Member save(Member member);

    boolean update(Member member);

    boolean delete(Integer memberId);

    Optional<Member> findById(Integer memberId);

    List<Member> findAll();

    Optional<Member> findByMemberCode(String memberCode);

    Optional<Member> findByEmail(String email);

    boolean existsByMemberCode(String memberCode);

}