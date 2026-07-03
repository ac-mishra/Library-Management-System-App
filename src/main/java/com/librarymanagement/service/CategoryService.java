package com.librarymanagement.service;

import com.librarymanagement.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(Category category);

    boolean update(Category category);

    boolean delete(Integer categoryId);

    Optional<Category> findById(Integer categoryId);

    List<Category> findAll();

}