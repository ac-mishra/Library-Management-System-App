package com.librarymanagement.service.impl;

import com.librarymanagement.dao.impl.BookReturnDAOImpl;
import com.librarymanagement.entity.BookReturn;
import com.librarymanagement.repository.BookReturnRepository;
import com.librarymanagement.service.BookReturnService;

import java.util.List;
import java.util.Optional;

/**
 * Book Return Service Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookReturnServiceImpl implements BookReturnService {

    private final BookReturnRepository repository;

    public BookReturnServiceImpl() {
        this.repository = new BookReturnDAOImpl();
    }

    @Override
    public BookReturn save(BookReturn bookReturn) {
        return repository.save(bookReturn);
    }

    @Override
    public boolean update(BookReturn bookReturn) {
        return repository.update(bookReturn);
    }

    @Override
    public boolean delete(Integer returnId) {
        return repository.delete(returnId);
    }

    @Override
    public Optional<BookReturn> findById(Integer returnId) {
        return repository.findById(returnId);
    }

    @Override
    public List<BookReturn> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<BookReturn> findByIssueId(Integer issueId) {
        return repository.findByIssueId(issueId);
    }

    @Override
    public boolean existsByIssueId(Integer issueId) {
        return repository.existsByIssueId(issueId);
    }

    @Override
    public List<BookReturn> findAllReturns() {
        return repository.findAllReturns();
    }
}