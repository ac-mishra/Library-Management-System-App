package com.librarymanagement.repository;

import com.librarymanagement.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Book entity.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitle(String title);

    List<Book> findByCategory(Integer categoryId);

    List<Book> findByAuthor(Integer authorId);

    boolean existsByIsbn(String isbn);

}