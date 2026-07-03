package com.librarymanagement.service;

import com.librarymanagement.entity.BookIssue;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for Book Issue.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface BookIssueService {

    /**
     * Issues a book.
     */
    BookIssue issueBook(BookIssue issue);

    /**
     * Updates issue information.
     */
    boolean update(BookIssue issue);

    /**
     * Deletes an issue record.
     */
    boolean delete(Integer issueId);

    /**
     * Finds issue by ID.
     */
    Optional<BookIssue> findById(Integer issueId);

    /**
     * Returns all issue records.
     */
    List<BookIssue> findAll();

    /**
     * Returns all active issued books.
     */
    List<BookIssue> findActiveIssues();

    /**
     * Returns all issues of a member.
     */
    List<BookIssue> findByMemberId(Integer memberId);

    /**
     * Returns all issues of a book.
     */
    List<BookIssue> findByBookId(Integer bookId);

    /**
     * Finds an active issue of a specific member and book.
     */
    Optional<BookIssue> findActiveIssue(
            Integer memberId,
            Integer bookId
    );

}