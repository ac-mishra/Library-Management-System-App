package com.librarymanagement.service.impl;

import com.librarymanagement.dao.impl.BookDAOImpl;
import com.librarymanagement.entity.Book;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.service.BookService;

import java.util.List;
import java.util.Optional;

/**
 * Book Service Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl() {
        this.repository = new BookDAOImpl();
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public boolean update(Book book) {
        return repository.update(book);
    }

    @Override
    public boolean delete(Integer bookId) {
        return repository.delete(bookId);
    }

    @Override
    public Optional<Book> findById(Integer bookId) {
        return repository.findById(bookId);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(Integer authorId) {
        return repository.findByAuthor(authorId);
    }

    @Override
    public List<Book> findByCategory(Integer categoryId) {
        return repository.findByCategory(categoryId);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return repository.existsByIsbn(isbn);
    }

}