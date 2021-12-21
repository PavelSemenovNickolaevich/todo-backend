package ru.java.backend.todo.todobackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/testCert")
    public String test() {
        return "test work";
    }
}
