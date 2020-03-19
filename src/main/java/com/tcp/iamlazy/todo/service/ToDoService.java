package com.tcp.iamlazy.todo.service;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.repository.ToDoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoMapper toDoMapper;

    public ToDoService(ToDoMapper toDoMapper){
        this.toDoMapper = toDoMapper;
    }

    public List<ToDo> getDayToDo(){
        List<ToDo> toDoList = toDoMapper.getDayToDo();

        return  toDoList;
    }
}
