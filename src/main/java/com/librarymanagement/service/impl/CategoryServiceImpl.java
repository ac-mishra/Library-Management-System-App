package com.librarymanagement.service.impl;

import com.librarymanagement.dao.impl.CategoryDAOImpl;
import com.librarymanagement.entity.Category;
import com.librarymanagement.repository.CategoryRepository;
import com.librarymanagement.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl
        implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl() {

        repository =
                new CategoryDAOImpl();

    }

    @Override
    public Category save(Category category) {

        return repository.save(category);

    }

    @Override
    public boolean update(Category category) {

        return repository.update(category);

    }

    @Override
    public boolean delete(Integer categoryId) {

        return repository.delete(categoryId);

    }

    @Override
    public Optional<Category> findById(Integer categoryId) {

        return repository.findById(categoryId);

    }

    @Override
    public List<Category> findAll() {

        return repository.findAll();

    }

}