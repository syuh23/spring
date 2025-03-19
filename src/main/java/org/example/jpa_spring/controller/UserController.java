package org.example.jpa_spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/signin")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.signIn(loginRequest);
    }

    @PostMapping("/user/edit")
    public String updateUser(@RequestBody EditUserRequest editUserRequest) {
        return userService.updateUser(editUserRequest);
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestBody UserIdRequest userIdRequest) {
        return userService.deleteUser(userIdRequest);
    }

    //TODO. log 붙이기
    //TODO. Response Entity로 반환하기 -> 어떤 것을 리턴해줄 것인지 잘 생각해보기
    //TODO. @RequestBody, @PathVariable? 찾아보기

}