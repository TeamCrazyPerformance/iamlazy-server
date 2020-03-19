package com.tcp.iamlazy.todo.contoller;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {
    private final ToDoService toDoService;
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/dayToDo")
    public ResponseEntity<List<ToDo>> getCompanyList(){
        return ResponseEntity.ok(toDoService.getDayToDo());
    }

}
