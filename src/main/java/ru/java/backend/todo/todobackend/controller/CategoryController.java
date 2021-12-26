package ru.java.backend.todo.todobackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.backend.todo.todobackend.entity.Category;
import ru.java.backend.todo.todobackend.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/id")
    public Category findBuId() {
        return categoryService.findById(60207L);
    }

    @PostMapping("/all")
    public List<Category> findAll(@RequestBody String email) {
        return categoryService.findAll(email);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        //Проверка на необязательные параметры
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("reduundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        //если передать пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {
        //Проверка на обязательные  параметры
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id ", HttpStatus.NOT_ACCEPTABLE);
        }
        //если передать пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);
        return new ResponseEntity(HttpStatus.OK);
    }
}
