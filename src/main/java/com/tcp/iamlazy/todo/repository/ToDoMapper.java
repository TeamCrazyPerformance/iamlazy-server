package com.tcp.iamlazy.todo.repository;

import com.tcp.iamlazy.todo.entity.ToDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ToDoMapper {
    // 일별 할일 목록 요청
    List<ToDo> getDayToDo();
}
