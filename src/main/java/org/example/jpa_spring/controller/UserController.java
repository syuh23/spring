package org.example.jpa_spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.dto.request.EditUserRequest;
import org.example.jpa_spring.dto.request.LoginRequest;
import org.example.jpa_spring.dto.request.UserIdRequest;
import org.example.jpa_spring.entity.User;
import org.example.jpa_spring.dto.request.SignUpRequest;
import org.example.jpa_spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.findUserByEmail(loginRequest);
        return userService.login(loginRequest, user.get());
    }

    @PostMapping("/user/edit")
    public String updateUser(@RequestBody EditUserRequest editUserRequest) {
        return userService.updateUser(editUserRequest);
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestBody UserIdRequest userIdRequest) {
        return userService.deleteUser(userIdRequest);
    }
}