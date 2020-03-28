package com.tcp.iamlazy.todo.contoller;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.service.ToDoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todo")
public class ToDoController {
    private final ToDoService toDoService;
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @GetMapping
    public ResponseEntity<List<ToDo>> getCompanyList(@AuthenticationPrincipal OAuth2User oAuth2User,
                                                     @RequestParam("date")String date) {



        return ResponseEntity.ok(toDoService.getDayToDo());
    }

}
