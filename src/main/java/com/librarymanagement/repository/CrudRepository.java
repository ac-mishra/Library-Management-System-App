package com.librarymanagement.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic CRUD repository interface.
 *
 * @param <T>  Entity type
 * @param <ID> Primary key type
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface CrudRepository<T, ID> {

    /**
     * Saves an entity.
     *
     * @param entity entity to save
     * @return saved entity
     */
    T save(T entity);

    /**
     * Updates an entity.
     *
     * @param entity entity to update
     * @return true if updated successfully
     */
    boolean update(T entity);

    /**
     * Deletes an entity by ID.
     *
     * @param id primary key
     * @return true if deleted
     */
    boolean delete(ID id);

    /**
     * Finds an entity by ID.
     *
     * @param id primary key
     * @return Optional entity
     */
    Optional<T> findById(ID id);

    /**
     * Returns all entities.
     *
     * @return list of entities
     */
    List<T> findAll();

}