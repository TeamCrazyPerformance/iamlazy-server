package com.tcp.iamlazy.todo.repository;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.entity.dto.ToDoRangeSearchCondition;
import com.tcp.iamlazy.todo.entity.dto.TodoDeleteCondition;
import com.tcp.iamlazy.todo.entity.dto.TodoIdxCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ToDoMapper {
    // 일별 할일 목록 요청
    List<ToDo> getDayToDo();

    List<ToDo> getToDoFromDate(ToDoRangeSearchCondition condition);

    int insertTodo(ToDo todo);

    ToDo getTodoById(TodoIdxCondition todoIdx);

    void updateTodoBy(ToDo todo);

    void deleteToDoById(TodoDeleteCondition todoIdx);
}
