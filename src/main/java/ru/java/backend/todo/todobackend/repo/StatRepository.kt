package ru.java.backend.todo.todobackend.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.java.backend.todo.todobackend.entity.Stat

@Repository
interface StatRepository : CrudRepository<Stat, Long> {
    fun findByUserEmail(email: String): Stat //связь один к одному
}