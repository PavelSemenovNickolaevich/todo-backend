package ru.java.backend.todo.todobackend.service

import org.springframework.stereotype.Service
import ru.java.backend.todo.todobackend.entity.Priority
import ru.java.backend.todo.todobackend.repo.PriorityRepository
import javax.transaction.Transactional

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service // все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции из данного метода откатятся (Rollback)
@Transactional
class PriorityService(  // сервис имеет право обращаться к репозиторию (БД)
    private val repository: PriorityRepository
) {
    fun findAll(email: String): List<Priority> {
        return repository.findByUserEmailOrderByIdAsc(email)
    }

    fun add(priority: Priority): Priority {
        return repository.save(priority) // метод save обновляет или создает новый объект, если его не было
    }

    fun update(priority: Priority): Priority {
        return repository.save(priority) // метод save обновляет или создает новый объект, если его не было
    }

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }

    fun findById(id: Long): Priority {
        return repository.findById(id).get() // т.к. возвращается Optional - можно получить объект методом get()
    }

    fun find(title: String?, email: String): List<Priority> {
        return repository.findByTitle(title, email)
    }
}