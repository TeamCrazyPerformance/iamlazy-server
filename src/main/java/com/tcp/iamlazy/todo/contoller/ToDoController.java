package com.tcp.iamlazy.todo.contoller;

import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.service.ToDoService;
import com.tcp.iamlazy.util.func.AuthFn;
import java.net.URI;
import java.util.List;
import java.util.function.BiFunction;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos")
@Slf4j
public class ToDoController {

    private final ToDoService toDoService;
    private final BiFunction<String, String, OAuth2AuthorizedClient> getUser;

    public ToDoController(ToDoService toDoService,
                          OAuth2AuthorizedClientService authorizedClientService) {
        this.toDoService = toDoService;
        getUser = authorizedClientService::loadAuthorizedClient;
    }


    @GetMapping
    public ResponseEntity<List<ToDo>> getToDoListOfDay(@NotNull OAuth2AuthenticationToken authentication,
                                                     @RequestParam(name = "date", required = false, defaultValue = "today")String date) {

        if (checkAuth(authentication)) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        final OAuth2AuthorizedClient client = AuthFn.from(authentication).to(getUser);

        if (client == null) {
            log.error("User registration information is null");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/oauth2/authorization/kakao"));
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }

        final String userName = client.getPrincipalName();

        final List<ToDo> toDoListFromDate = toDoService.getToDoListFromDate(userName, date);

        return ResponseEntity.ok(toDoListFromDate);
    }

    /**
     * @Fixme: 누군가 이걸 우아하게 바꿔줄 수 있을 것 같은데..
     * @param authentication
     * @return
     */
    private boolean checkAuth(@NotNull OAuth2AuthenticationToken authentication) {
        if (authentication == null) {
            log.error("authentication cannot be null");
            return true;
        }
        return false;
    }

    @PostMapping
    public ResponseEntity<String> createToDoList(@NotNull OAuth2AuthenticationToken authentication,
                                                 @RequestBody ToDo todo) {
        if (checkAuth(authentication)) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        final OAuth2AuthorizedClient client = AuthFn.from(authentication).to(getUser);

        if (client == null) {
            log.error("User registration information is null");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/oauth2/authorization/kakao"));
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }

        toDoService.addTodoTo(todo, client.getPrincipalName());

        return ResponseEntity.ok("Location isn't known.");
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ToDo> getToDo(@NotNull OAuth2AuthenticationToken authentication,
                                        @PathVariable("todoId")int todoIdx) {

        if (checkAuth(authentication)) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        final OAuth2AuthorizedClient client = AuthFn.from(authentication).to(getUser);

        if (client == null) {
            log.error("User registration information is null");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/oauth2/authorization/kakao"));
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }

        final String userName = client.getPrincipalName();

        ToDo toDo = toDoService.getToDoById(todoIdx, userName);

        return ResponseEntity.ok(toDo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<ToDo> updateToDo(@NotNull OAuth2AuthenticationToken authentication,
                                        @PathVariable("todoId")int todoIdx,
                                        @RequestBody ToDo todo) {

        if (checkAuth(authentication)) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        final OAuth2AuthorizedClient client = AuthFn.from(authentication).to(getUser);

        if (client == null) {
            log.error("User registration information is null");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/oauth2/authorization/kakao"));
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }

        final String userName = client.getPrincipalName();
        todo.setTodoIdx(todoIdx);
        todo.setUserId(Integer.parseInt(userName));

        toDoService.updateTodo(todo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ToDo> deleteToDo(@NotNull OAuth2AuthenticationToken authentication,
                                           @PathVariable("todoId")int todoIdx) {

        if (checkAuth(authentication)) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        final OAuth2AuthorizedClient client = AuthFn.from(authentication).to(getUser);

        if (client == null) {
            log.error("User registration information is null");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create("/oauth2/authorization/kakao"));
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }

        final String userName = client.getPrincipalName();

        toDoService.deleteTodo(todoIdx, userName);

        return ResponseEntity.ok().build();
    }


}
