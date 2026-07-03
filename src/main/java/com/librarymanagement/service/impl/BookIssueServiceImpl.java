package com.librarymanagement.service.impl;

import com.librarymanagement.dao.impl.BookIssueDAOImpl;
import com.librarymanagement.entity.BookIssue;
import com.librarymanagement.repository.BookIssueRepository;
import com.librarymanagement.service.BookIssueService;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for Book Issue.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookIssueServiceImpl
        implements BookIssueService {

    private final BookIssueRepository repository;

    public BookIssueServiceImpl() {

        repository = new BookIssueDAOImpl();

    }

    @Override
    public BookIssue issueBook(BookIssue issue) {

        return repository.save(issue);

    }

    @Override
    public boolean update(BookIssue issue) {

        return repository.update(issue);

    }

    @Override
    public boolean delete(Integer issueId) {

        return repository.delete(issueId);

    }

    @Override
    public Optional<BookIssue> findById(Integer issueId) {

        return repository.findById(issueId);

    }

    @Override
    public List<BookIssue> findAll() {

        return repository.findAll();

    }

    @Override
    public List<BookIssue> findActiveIssues() {

        return repository.findActiveIssues();

    }

    @Override
    public List<BookIssue> findByMemberId(Integer memberId) {

        return repository.findByMemberId(memberId);

    }

    @Override
    public List<BookIssue> findByBookId(Integer bookId) {

        return repository.findByBookId(bookId);

    }

    @Override
    public Optional<BookIssue> findActiveIssue(
            Integer memberId,
            Integer bookId) {

        return repository.findActiveIssue(
                memberId,
                bookId
        );

    }

}