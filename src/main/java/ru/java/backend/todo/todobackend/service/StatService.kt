package ru.java.backend.todo.todobackend.service

import org.springframework.stereotype.Service
import ru.java.backend.todo.todobackend.entity.Stat
import ru.java.backend.todo.todobackend.repo.StatRepository
import javax.transaction.Transactional

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service // все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
@Transactional
class StatService(  // сервис имеет право обращаться к репозиторию (БД)
    private val repository: StatRepository
) {
    fun findStat(email: String): Stat {
        return repository.findByUserEmail(email)
    }
}