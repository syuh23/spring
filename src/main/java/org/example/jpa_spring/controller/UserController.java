package org.example.jpa_spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.dto.request.EditUserRequest;
import org.example.jpa_spring.dto.request.SignInRequest;
import org.example.jpa_spring.dto.request.UserIdRequest;
import org.example.jpa_spring.dto.request.SignUpRequest;
import org.example.jpa_spring.dto.response.SignInResponse;
import org.example.jpa_spring.error.DataResponseType;
import org.example.jpa_spring.error.ResponseEnum;
import org.example.jpa_spring.error.ResponseType;
import org.example.jpa_spring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseType> signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);

        return ResponseType.entity(ResponseEnum.SUCCESS);
    }

    @PostMapping("/signin")
    public ResponseEntity<DataResponseType<SignInResponse>> login(@RequestBody SignInRequest signInRequest) {
        userService.signIn(signInRequest);

        SignInResponse signInResponse = SignInResponse.builder()
                .token("Token Example")
                .name("Name Example")
                .build();

        return DataResponseType.entity(ResponseEnum.SUCCESS, signInResponse);
    }

    @PostMapping("/user/edit")
    public String updateUser(@RequestBody EditUserRequest editUserRequest) {
        return userService.updateUser(editUserRequest);
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestBody UserIdRequest userIdRequest) {
        return userService.deleteUser(userIdRequest);
    }

    //TODO. @RequestBody, @PathVariable? 찾아보기

}