package com.tcp.iamlazy.todo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tcp.iamlazy.configuration.security.CustomUserDetailsService;
import com.tcp.iamlazy.configuration.security.UserPrincipal;
import com.tcp.iamlazy.todo.contoller.ToDoController;
import com.tcp.iamlazy.todo.service.ToDoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ToDoController(toDoService))
        .setCustomArgumentResolvers(putAuthenticationPrincipal)
        .build();
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

  @Test
//  @WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
//  @WithUserDetails(value = "1313651068", userDetailsServiceBeanName = "customUserDetailsService")
  public void Todo_데이터_업데이트_타당성_검사_WhenAllFieldAreGoodShouldReturnSuccess()
      throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders
                             .put("/todos/{id}", "3")
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
        .andExpect(status().isOk());

  }

  private HandlerMethodArgumentResolver putAuthenticationPrincipal = new HandlerMethodArgumentResolver() {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
      return parameter.getParameterType().isAssignableFrom(UserPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
      return new UserPrincipal(3L, "email", "pass", AuthorityUtils.createAuthorityList("USER"));
    }
  };
}
