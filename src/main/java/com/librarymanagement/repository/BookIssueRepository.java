package com.librarymanagement.repository;

import com.librarymanagement.entity.BookIssue;

import java.util.List;
import java.util.Optional;

/**
 * Repository Interface for Book Issue.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface BookIssueRepository
        extends CrudRepository<BookIssue, Integer> {

    /**
     * Finds all active issued books.
     */
    List<BookIssue> findActiveIssues();

    /**
     * Finds issues by member.
     */
    List<BookIssue> findByMemberId(Integer memberId);

    /**
     * Finds issues by book.
     */
    List<BookIssue> findByBookId(Integer bookId);

    /**
     * Finds active issue by Book and Member.
     */
    Optional<BookIssue> findActiveIssue(
            Integer memberId,
            Integer bookId
    );

}