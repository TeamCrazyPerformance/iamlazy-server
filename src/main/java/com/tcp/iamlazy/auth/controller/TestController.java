package com.tcp.iamlazy.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created with intellij IDEA. by 2020 03 28/03/2020 12:45 오전 28 User we at 00 45 To change this
 * template use File | Settings | File Templates.
 */
@Controller
@Slf4j
public class TestController {

  @GetMapping("/login/suc")
  public String loginSuc(ModelMap model,
                               @ModelAttribute("name1") String flashAttribute) {
    log.info("flash was : {}", flashAttribute);
    model.addAttribute("name", flashAttribute);
    model.put("name", flashAttribute);
      return "suc";
  }

}
