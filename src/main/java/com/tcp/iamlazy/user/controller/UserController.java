package com.tcp.iamlazy.user.controller;

import com.tcp.iamlazy.bean.resolve.RestTemplateResolver;
import com.tcp.iamlazy.user.service.UserService;
import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private final UserService userService;
    private final RestTemplate restTemplate;

    public UserController(UserService userService) {
        this.userService = userService;
        this.restTemplate = new RestTemplateResolver().resolve();
    }

    @GetMapping(value = "/login", produces = "text/html")
    public ResponseEntity<String> loginUser(@RequestParam(required = false, name = "type", defaultValue = "default")String param,
                                            HttpServletResponse response) throws IOException {

        if (param.equalsIgnoreCase("kakao")) {
            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<String> request = new HttpEntity<>(null, headers);
//            final ResponseEntity<String> response = restTemplate
//                .exchange("http://localhost:8080/oauth2/authorization/kakao", new OAuth2UserRequestEntityConverter());

//            return ResponseEntity.ok(response.getBody());
//            response.sendRedirect("http://localhost:8080/oauth2/authorization/kakao");
            headers.setLocation(URI.create("/oauth2/authorization/kakao"));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("THE " + param + " login is not supported.");
        }
    }

}
