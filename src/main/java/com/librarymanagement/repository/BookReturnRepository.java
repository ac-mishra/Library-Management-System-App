package com.librarymanagement.repository;

import com.librarymanagement.entity.BookReturn;

import java.util.List;
import java.util.Optional;

/**
 * Repository Interface for Book Return.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface BookReturnRepository
        extends CrudRepository<BookReturn, Integer> {

    /**
     * Finds a return record by Issue ID.
     */
    Optional<BookReturn> findByIssueId(Integer issueId);

    /**
     * Checks whether a book has already been returned.
     */
    boolean existsByIssueId(Integer issueId);

    /**
     * Returns all returned books.
     */
    List<BookReturn> findAllReturns();

}