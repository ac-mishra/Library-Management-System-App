package com.librarymanagement.service;

import com.librarymanagement.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * Book Service Interface.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface BookService {

    Book save(Book book);

    boolean update(Book book);

    boolean delete(Integer bookId);

    Optional<Book> findById(Integer bookId);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(Integer authorId);

    List<Book> findByCategory(Integer categoryId);

    boolean existsByIsbn(String isbn);

}