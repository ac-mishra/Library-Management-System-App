package com.librarymanagement.repository;

import com.librarymanagement.entity.Member;

import java.util.Optional;

public interface MemberRepository
        extends CrudRepository<Member, Integer> {

    Optional<Member> findByMemberCode(String memberCode);

    Optional<Member> findByEmail(String email);

    boolean existsByMemberCode(String memberCode);

}