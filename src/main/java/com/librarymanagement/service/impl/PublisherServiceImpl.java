package com.librarymanagement.service.impl;

import com.librarymanagement.dao.impl.PublisherDAOImpl;
import com.librarymanagement.entity.Publisher;
import com.librarymanagement.repository.PublisherRepository;
import com.librarymanagement.service.PublisherService;

import java.util.List;
import java.util.Optional;

/**
 * Publisher Service Implementation.
 *
 * @author Amrit Chandan Mishra
 * @version 1.0.0
 */
public class PublisherServiceImpl
        implements PublisherService {

    private final PublisherRepository repository;

    public PublisherServiceImpl() {

        repository = new PublisherDAOImpl();

    }

    @Override
    public Publisher save(Publisher publisher) {

        return repository.save(publisher);

    }

    @Override
    public boolean update(Publisher publisher) {

        return repository.update(publisher);

    }

    @Override
    public boolean delete(Integer publisherId) {

        return repository.delete(publisherId);

    }

    @Override
    public Optional<Publisher> findById(Integer publisherId) {

        return repository.findById(publisherId);

    }

    @Override
    public List<Publisher> findAll() {

        return repository.findAll();

    }

}