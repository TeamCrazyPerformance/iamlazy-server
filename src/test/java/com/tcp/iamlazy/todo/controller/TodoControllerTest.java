package com.tcp.iamlazy.todo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tcp.iamlazy.todo.contoller.ToDoController;
import com.tcp.iamlazy.todo.service.ToDoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created with intellij IDEA. by 2020 04 2020/04/09 12:41 오전 09 User we at 00 41 To change this
 * template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {

  private MockMvc mockMvc;

  @Mock
  private ToDoService toDoService;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ToDoController(toDoService)).build();
    /* maybe you can use webMvcTest instead */
  }

  @Test
  public void Todo_데이터_타당성_검사_WhenRepeatUnitWasGivenNotWithin7_1_30_Should_ReturnFalseMessage()
      throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders
      .post("/todos")
                         .accept(MediaType.APPLICATION_JSON)
                             .contentType(MediaType.APPLICATION_JSON)
                             .content("{\n"
                                          + "\t\"todoTitle\" : \"title\",\n"
                                          + "\t\"todoContent\" : \"content\",\n"
                                          + "\t\"todoDate\" : \"2020-03-29\",\n"
                                          + "\t\"repeatableYN\" : 1,\n"
                                          + "\t\"repeatUnit\" : 12,\n"
                                          + "\t\"startDate\" : \"2020-03-29\",\n"
                                          + "\t\"endDate\" : \"2020-04-30\",\n"
                                          + "\t\"weekDay\" : 2,\n"
                                          + "\t\"monthDay\" : 3,\n"
                                          + "\t\"finish\" : 0\n"
                                          + "}"))
//        .andExpect(model().errorCount(0))
        .andExpect(status().isBadRequest())
    ;
  }

  @Test
  public void Todo_데이터_타당성_검사_WhenRepeatableYNWasNotGivenRepeatUnit_Should_BeNull()
      throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders
                             .post("/todos")
                             .accept(MediaType.APPLICATION_JSON)
                             .contentType(MediaType.APPLICATION_JSON)
                             .content("{\n"
                                          + "\t\"todoTitle\" : \"title\",\n"
                                          + "\t\"todoContent\" : \"content\",\n"
                                          + "\t\"todoDate\" : \"2020-03-29\",\n"
                                          + "\t\"repeatUnit\" : 12,\n"
                                          + "\t\"startDate\" : \"2020-03-29\",\n"
                                          + "\t\"endDate\" : \"2020-04-30\",\n"
                                          + "\t\"weekDay\" : 2,\n"
                                          + "\t\"monthDay\" : 3,\n"
                                          + "\t\"finish\" : 0\n"
                                          + "}"))
        .andExpect(status().isBadRequest())
    ;
  }

  @Test
  public void Todo_데이터_타당성_검사_WhenRepeatableYNWasFalseRepeatUnit_Should_BeNull()
      throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders
                             .post("/todos")
                             .accept(MediaType.APPLICATION_JSON)
                             .contentType(MediaType.APPLICATION_JSON)
                             .content("{\n"
                                          + "\t\"todoTitle\" : \"title\",\n"
                                          + "\t\"todoContent\" : \"content\",\n"
                                          + "\t\"todoDate\" : \"2020-03-29\",\n"
                                          + "\t\"repeatableYN\" : 0,\n"
                                          + "\t\"repeatUnit\" : 12,\n"
                                          + "\t\"startDate\" : \"2020-03-29\",\n"
                                          + "\t\"endDate\" : \"2020-04-30\",\n"
                                          + "\t\"weekDay\" : 2,\n"
                                          + "\t\"monthDay\" : 3,\n"
                                          + "\t\"finish\" : 0\n"
                                          + "}"))
        .andExpect(status().isBadRequest())
    ;
  }

  @Test
  public void Todo_데이터_타당성_검사_WhenAllFieldAreGoodShouldReturnSuccess()
      throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders
                                                         .post("/todos")
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content("{\n"
                                                                      + "\t\"todoTitle\" : \"title\",\n"
                                                                      + "\t\"todoContent\" : \"content\",\n"
                                                                      + "\t\"todoDate\" : \"2020-03-29\",\n"
                                                                      + "\t\"repeatableYN\" : 1,\n"
                                                                      + "\t\"repeatUnit\" : 1,\n"
                                                                      + "\t\"startDate\" : \"2020-03-29\",\n"
                                                                      + "\t\"endDate\" : \"2020-04-30\",\n"
                                                                      + "\t\"weekDay\" : 2,\n"
                                                                      + "\t\"monthDay\" : 3,\n"
                                                                      + "\t\"finish\" : 0\n"
                                                                      + "}"))
        .andExpect(status().isCreated());

  }
}
