package com.tcp.iamlazy.todo.contoller;

import com.tcp.iamlazy.auth.controller.payload.ApiResponse;
import com.tcp.iamlazy.auth.controller.payload.todo.TodoPostResponse;
import com.tcp.iamlazy.configuration.security.CurrentUser;
import com.tcp.iamlazy.configuration.security.UserPrincipal;
import com.tcp.iamlazy.todo.entity.ToDo;
import com.tcp.iamlazy.todo.service.ToDoService;
import com.tcp.iamlazy.util.valid.RequestResultValidationProcessor;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/todos")
@Slf4j
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    //@ApiParam(value = "title : 주소(필수)\n"
    //      + "content : 수집 시간 (00~23)\n"
    //      + "data : 수집 분 (00~59)\n"

    @GetMapping
    public ResponseEntity<List<ToDo>> getToDoListOfDay(@CurrentUser UserPrincipal userPrincipal,
                                                     @RequestParam(name = "date", required = false, defaultValue = "today")String date) {

        final String userName = userPrincipal.getUsername();

        final List<ToDo> toDoListFromDate = toDoService.getToDoListFromDate(userName, date);

        return ResponseEntity.ok(toDoListFromDate);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> createToDoList(@CurrentUser UserPrincipal userPrincipal,
                                                      @ApiParam(value = "title text : 할일 제목(필수, 공백 가능)\n"
                                                          + "content text : 할일 내용(필수, 공백 가능)\n"
                                                          + "todoDate : 등록 일자(필수, yyyy-MM-dd 포맷)\n"
                                                          + "repeatableYN : 반복 여부( 이 값이 참일 경우에만 아래의 finish 필드를 제외한 값이 설정되어야 한다 )\n"
                                                          + "repeatUnit : 반복 단위 설정 그 값은 1 : 매일, 7 : 매주, 30 : 매월 중 하나를 설정(repeatableYN 이 참일 때)\n"
                                                          + "startDate : 시작 날짜(yyyy-MM-dd)\n"
                                                          + "endDate : 종료 날짜(yyyy-MM-dd)\n"
                                                          + "weekDay : repeatUnit 이 7인 경우 요일 (0 ~ 6 : 일 ~ 토)\n"
                                                          + "monthDay : repeatUnit 이 30인 경우 일자\n"
                                                          + "finish : 완료 여부(진위값)")
                                                      @Valid @RequestBody ToDo todo,
                                                      Errors errors) {
        if (errors.hasErrors()) {
            return RequestResultValidationProcessor.returnErrorResponse(errors);
        }

        toDoService.addTodoTo(todo, userPrincipal.getUsername());

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/todos/{todoId}")
            .buildAndExpand(todo.getTodoIdx()).toUri();

        return ResponseEntity.created(location)
            .body(new TodoPostResponse(true, "Todo registered successfully@",
                                       todo.getTodoIdx()));
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ToDo> getToDo(@CurrentUser UserPrincipal userPrincipal,
                                        @PathVariable("todoId")int todoIdx) {

        final String userName = userPrincipal.getUsername();

        ToDo toDo = toDoService.getToDoById(todoIdx, userName);

        return ResponseEntity.ok(toDo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<ApiResponse> updateToDo(@CurrentUser UserPrincipal userPrincipal,
                                                  @PathVariable("todoId")int todoIdx,
                                                  @Valid @RequestBody ToDo todo,
                                                  Errors errors) {
        if (errors.hasErrors()) {
            return RequestResultValidationProcessor.returnErrorResponse(errors);
        }

        final String userName = userPrincipal.getUsername();
        todo.setTodoIdx(todoIdx);
        todo.setUserId(Integer.parseInt(userName));

        toDoService.updateTodo(todo);

        return ResponseEntity.ok().body(new ApiResponse(true, "Todo updated successfully@"));

    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ApiResponse> deleteToDo(@CurrentUser UserPrincipal userPrincipal,
                                                  @PathVariable("todoId")int todoIdx) {

        final String userName = userPrincipal.getUsername();

        toDoService.deleteTodo(todoIdx, userName);

        return ResponseEntity.ok().body(new ApiResponse(true, "Todo deleted successfully@"));
    }


}
