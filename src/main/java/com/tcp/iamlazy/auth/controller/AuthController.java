package com.tcp.iamlazy.auth.controller;

import com.tcp.iamlazy.auth.controller.payload.ApiResponse;
import com.tcp.iamlazy.auth.controller.payload.AuthResponse;
import com.tcp.iamlazy.auth.controller.payload.LoginRequest;
import com.tcp.iamlazy.auth.controller.payload.SignUpRequest;
import com.tcp.iamlazy.configuration.security.TokenProvider;
import com.tcp.iamlazy.configuration.security.oauth2.AuthProvider;
import com.tcp.iamlazy.configuration.security.oauth2.exception.BadRequestException;
import com.tcp.iamlazy.user.entity.User;
import com.tcp.iamlazy.user.repository.UserMapper;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created with intellij IDEA. by 2020 04 2020/04/01 1:18 오전 01 User we at 01 18 To change this
 * template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  private final TokenProvider tokenProvider;

  public AuthController(
      AuthenticationManager authenticationManager,
      UserMapper userMapper,
      PasswordEncoder passwordEncoder,
      TokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = tokenProvider.createToken(authentication);
    return ResponseEntity.ok(new AuthResponse(token));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    if(userMapper.findByEmail(signUpRequest.getEmail()).isPresent()) {
      throw new BadRequestException("Email address already in use.");
    }

    User user = new User();
    user.setUserName(signUpRequest.getName());
    user.setUserEmail(signUpRequest.getEmail());
    user.setPassword(signUpRequest.getPassword());
    user.setProvider(AuthProvider.local);

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userMapper.insertUser(user);

    User result = user;

    URI location = ServletUriComponentsBuilder
    .fromCurrentContextPath().path("/user/me")
    .buildAndExpand(result.getUserKakaoID()).toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "User registered successfully@"));
  }

}
