package ru.java.backend.todo.todobackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.java.backend.todo.todobackend.entity.Category;


//ооп: абстракция - реализация - здесь описываем все доступные доступы к данным
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
