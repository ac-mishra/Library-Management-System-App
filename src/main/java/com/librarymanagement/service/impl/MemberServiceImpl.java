package com.librarymanagement.service.impl;

import com.librarymanagement.dao.impl.MemberDAOImpl;
import com.librarymanagement.entity.Member;
import com.librarymanagement.repository.MemberRepository;
import com.librarymanagement.service.MemberService;

import java.util.List;
import java.util.Optional;

/**
 * Member Service Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class MemberServiceImpl
        implements MemberService {

    private final MemberRepository repository;

    public MemberServiceImpl() {

        repository = new MemberDAOImpl();

    }

    @Override
    public Member save(Member member) {

        return repository.save(member);

    }

    @Override
    public boolean update(Member member) {

        return repository.update(member);

    }

    @Override
    public boolean delete(Integer memberId) {

        return repository.delete(memberId);

    }

    @Override
    public Optional<Member> findById(Integer memberId) {

        return repository.findById(memberId);

    }

    @Override
    public List<Member> findAll() {

        return repository.findAll();

    }

    @Override
    public Optional<Member> findByMemberCode(
            String memberCode) {

        return repository.findByMemberCode(
                memberCode
        );

    }

    @Override
    public Optional<Member> findByEmail(
            String email) {

        return repository.findByEmail(
                email
        );

    }

    @Override
    public boolean existsByMemberCode(
            String memberCode) {

        return repository.existsByMemberCode(
                memberCode
        );

    }

}