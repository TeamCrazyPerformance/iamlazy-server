package com.tcp.iamlazy.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tcp.iamlazy.dto.Company;
import com.tcp.iamlazy.service.CompanyService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created with intellij IDEA. by 2020 03 17/03/2020 2:42 오전 17 User we at 02 42 To change this
 * template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(IamLazyController.class)
public class IamLazyControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CompanyService companyService;

  @Test
  public void whenGetMethodCalled_Valid_Company_instace_list_Should_Be_returned() throws Exception {

    Company company = new Company();
    company.setCompanyNo(340);

    given(companyService.get()).willReturn(Arrays.asList(company));

    mvc.perform(get("/companies")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].companyNo", is(340)))
    ;

    verify(companyService, times(1)).get();
  }

}
