package com.librarymanagement.service;

import com.librarymanagement.entity.Author;

import java.util.List;
import java.util.Optional;

/**
 * Author Service Interface.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface AuthorService {

    Author save(Author author);

    boolean update(Author author);

    boolean delete(Integer authorId);

    Optional<Author> findById(Integer authorId);

    List<Author> findAll();

}