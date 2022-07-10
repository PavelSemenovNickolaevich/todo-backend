package ru.java.backend.todo.todobackend.entity

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

/*

общая статистика по задачам (незвисимо от категорий задач)

 */
@Entity
@Table(name = "stat", schema = "todolist", catalog = "postgres")
class Stat {
    // в этой таблице всего 1 запись, которая обновляется (но никогда не удаляется)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "completed_total", updatable = false)
    val completedTotal: Long? = null  // значение задается в триггере в БД

    @Column(name = "uncompleted_total", updatable = false)
    val uncompletedTotal: Long? = null // значение задается в триггере в БД

    @OneToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
    val user: User? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val stat = o as Stat
        return id == stat.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}