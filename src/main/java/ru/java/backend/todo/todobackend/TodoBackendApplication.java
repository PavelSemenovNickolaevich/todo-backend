package ru.java.backend.todo.todobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class TodoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoBackendApplication.class, args);
    }

}
