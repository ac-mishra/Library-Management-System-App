package com.librarymanagement.service;

import com.librarymanagement.entity.BookReturn;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for Book Return.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface BookReturnService {

    /**
     * Saves a return record.
     */
    BookReturn save(BookReturn bookReturn);

    /**
     * Updates a return record.
     */
    boolean update(BookReturn bookReturn);

    /**
     * Deletes a return record.
     */
    boolean delete(Integer returnId);

    /**
     * Finds return by ID.
     */
    Optional<BookReturn> findById(Integer returnId);

    /**
     * Returns all return records.
     */
    List<BookReturn> findAll();

    /**
     * Finds return by Issue ID.
     */
    Optional<BookReturn> findByIssueId(Integer issueId);

    /**
     * Checks whether the issue has already been returned.
     */
    boolean existsByIssueId(Integer issueId);

    /**
     * Returns all returned books.
     */
    List<BookReturn> findAllReturns();

}