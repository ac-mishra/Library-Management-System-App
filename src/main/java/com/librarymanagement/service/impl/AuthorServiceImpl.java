package com.librarymanagement.service.impl;

import com.librarymanagement.dao.impl.AuthorDAOImpl;
import com.librarymanagement.entity.Author;
import com.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.service.AuthorService;

import java.util.List;
import java.util.Optional;

/**
 * Author Service Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl() {

        repository = new AuthorDAOImpl();

    }

    @Override
    public Author save(Author author) {

        return repository.save(author);

    }

    @Override
    public boolean update(Author author) {

        return repository.update(author);

    }

    @Override
    public boolean delete(Integer authorId) {

        return repository.delete(authorId);

    }

    @Override
    public Optional<Author> findById(Integer authorId) {

        return repository.findById(authorId);

    }

    @Override
    public List<Author> findAll() {

        return repository.findAll();

    }

}