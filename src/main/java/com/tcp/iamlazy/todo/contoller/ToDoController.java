package com.tcp.iamlazy.todo.contoller;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {
    private final ToDoService toDoService;
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/dayToDo/{date}")
    public ResponseEntity<List<ToDo>> getCompanyList(){
        HashMap<String, Object> dayToDo = new HashMap<>();

        dayToDo.put("dayToDoItem", toDoService.getDayToDo());

        return new ResponseEntity(dayToDo, HttpStatus.OK);
    }

}
