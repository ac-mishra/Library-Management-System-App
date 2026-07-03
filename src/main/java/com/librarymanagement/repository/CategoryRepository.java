package com.librarymanagement.repository;

import com.librarymanagement.entity.Category;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Category.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface CategoryRepository
        extends CrudRepository<Category, Integer> {

    /**
     * Find category by name.
     *
     * @param categoryName category name
     * @return optional category
     */
    Optional<Category> findByName(String categoryName);

    /**
     * Get all categories.
     *
     * @return category list
     */
    @Override
    List<Category> findAll();

}