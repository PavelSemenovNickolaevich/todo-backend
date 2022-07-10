package ru.java.backend.todo.todobackend.service

import org.springframework.stereotype.Service
import ru.java.backend.todo.todobackend.entity.Category
import ru.java.backend.todo.todobackend.repo.CategoryRepository
import javax.transaction.Transactional

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service // все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе выполняются несолько SQL запросов и возникнет исключение - то все выполненные операции откатятся (Rollback)
@Transactional

// работает встроенный механизм DI из Spring, который при старте приложения подставит в эту переменную нужные класс-реализацию
class CategoryService(private val repository: CategoryRepository) { // сервис имеет право обращаться к репозиторию (БД)

    fun findAll(email: String): List<Category> {
        return repository.findByUserEmailOrderByTitleAsc(email)
    }

    fun add(category: Category): Category {
        return repository.save(category) // метод save обновляет или создает новый объект, если его не было
    }

    fun update(category: Category): Category {
        return repository.save(category) // метод save обновляет или создает новый объект, если его не было
    }

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }

    // поиск категорий пользователя по названию
    fun findByTitle(text: String?, email: String): List<Category> {
        return repository.findByTitle(text, email)
    }

    // поиск категории по ID
    fun findById(id: Long): Category {
        return repository.findById(id).get() // т.к. возвращается Optional - можно получить объект методом get()
    }

}