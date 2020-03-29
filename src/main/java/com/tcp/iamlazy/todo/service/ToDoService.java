package com.tcp.iamlazy.todo.service;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.entity.dto.ToDoRangeSearchCondition;
import com.tcp.iamlazy.todo.entity.dto.TodoIdxCondition;
import com.tcp.iamlazy.todo.repository.ToDoMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ToDoService {
    private final ToDoMapper toDoMapper;

    public ToDoService(ToDoMapper toDoMapper){
        this.toDoMapper = toDoMapper;
    }

    public List<ToDo> getDayToDo(){
        List<ToDo> toDoList = toDoMapper.getDayToDo();

        return  toDoList;
    }

    @Transactional
    public List<ToDo> getToDoListFromDate(String userName, String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.ofInstant(format.parse(date).toInstant(),
                                                    ZoneId.systemDefault());
        } catch (ParseException e) {
            log.error("Parameter date can't be parsed.. check valid format yyyyMMdd : {}", e.getMessage());
            localDateTime = LocalDateTime.now();
        }

        ToDoRangeSearchCondition condition = new ToDoRangeSearchCondition(userName, localDateTime);

        return toDoMapper.getToDoFromDate(condition);
    }

    @Transactional
    public int addTodoTo(ToDo todo, String principalName) {
        todo.setUserId(Integer.parseInt(principalName));
        return toDoMapper.insertTodo(todo);

    }

    @Transactional
    public ToDo getToDoById(int todoIdx, String principalName) {
        TodoIdxCondition con = new TodoIdxCondition(principalName, todoIdx);
        return toDoMapper.getTodoById(con);
    }

    @Transactional
    public void updateTodo(ToDo todo) {
        toDoMapper.updateTodoBy(todo);
    }

    @Transactional
    public void deleteTodo(int todoIdx) {
        toDoMapper.deleteToDoById(todoIdx);
    }
}
