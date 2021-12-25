package ru.java.backend.todo.todobackend.service;


import org.springframework.stereotype.Service;
import ru.java.backend.todo.todobackend.entity.Category;
import ru.java.backend.todo.todobackend.repo.CategoryRepository;

import javax.transaction.Transactional;

@Service


@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(long id) {
        return repository.findById(id).get();
    }
}
