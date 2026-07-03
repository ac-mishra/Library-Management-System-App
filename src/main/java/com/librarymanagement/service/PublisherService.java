package com.librarymanagement.service;

import com.librarymanagement.entity.Publisher;

import java.util.List;
import java.util.Optional;

/**
 * Publisher Service.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public interface PublisherService {

    Publisher save(Publisher publisher);

    boolean update(Publisher publisher);

    boolean delete(Integer publisherId);

    Optional<Publisher> findById(Integer publisherId);

    List<Publisher> findAll();

}