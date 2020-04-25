package com.tcp.iamlazy.todo.service;

import static com.tcp.iamlazy.util.date.DateFormatCatcher.getLocalDateTime;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.entity.dto.ToDoRangeSearchCondition;
import com.tcp.iamlazy.todo.entity.dto.TodoDeleteCondition;
import com.tcp.iamlazy.todo.entity.dto.TodoIdxCondition;
import com.tcp.iamlazy.todo.repository.ToDoMapper;
import com.tcp.iamlazy.util.date.DateFormatCatcher;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ToDoService {

    @Value("${app.date.param.format}")
    private String dateStringFormat;

    private final ToDoMapper toDoMapper;

    public ToDoService(ToDoMapper toDoMapper){
        this.toDoMapper = toDoMapper;
    }

    @Transactional
    public List<ToDo> getToDoListFromDate(String userName, String date) {
        LocalDateTime localDateTime = getLocalDateTime(date,dateStringFormat);
        log.info("Translated date was {}", localDateTime);

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
    public void deleteTodo(int todoIdx, String userName) {
        TodoDeleteCondition con = new TodoDeleteCondition(Integer.parseInt(userName), todoIdx);
        toDoMapper.deleteToDoById(con);
    }
}
