package ru.java.backend.todo.todobackend.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.java.backend.todo.todobackend.entity.Task
import ru.java.backend.todo.todobackend.repo.TaskRepository
import java.util.*
import javax.transaction.Transactional

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service // все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
@Transactional
class TaskService(  // сервис имеет право обращаться к репозиторию (БД)
    private val repository: TaskRepository
) {
    fun findAll(email: String): List<Task> {
        return repository.findByUserEmailOrderByTitleAsc(email)
    }

    fun add(task: Task): Task {
        return repository.save(task) // метод save обновляет или создает новый объект, если его не было
    }

    fun update(task: Task): Task {
        return repository.save(task) // метод save обновляет или создает новый объект, если его не было
    }

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }

    fun findByParams(
        text: String?,
        completed: Boolean?,
        priorityId: Long?,
        categoryId: Long?,
        email: String,
        dateFrom: Date?,
        dateTo: Date?,
        paging: PageRequest
    ): Page<Task> {
        return repository.findByParams(text, completed, priorityId, categoryId, email, dateFrom, dateTo, paging)
    }

    fun findById(id: Long): Task {
        return repository.findById(id).get() // т.к. возвращается Optional - можно получить объект методом get()
    }
}