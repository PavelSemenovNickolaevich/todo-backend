package ru.java.backend.todo.todobackend.entity


import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/*

Вся активность пользователя (активация аккаунта, другие действия по необходимости)

*/
@Entity
@Table(name = "activity", schema = "todolist", catalog = "postgres")
class Activity {
    // название таблицы будет браться автоматически по названию класса с маленькой буквы: activity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Type(type = "org.hibernate.type.NumericBooleanType") // для автоматической конвертации числа в true/false
    val activated // становится true только после подтверждения активации пользователем (обратно false уже стать не может по логике)
            : Boolean? = null

    @Column(updatable = false)
    val uuid // создается только один раз с помощью триггера в БД
            : String? = null

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val activity = o as Activity
        return id == activity.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}