package org.example.jpa_spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.dto.request.LoginRequest;
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

    //TODO. SRP를 생각하면서 코드 작성
    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        //TODO. Email 중복 확인
        userService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.findUser(loginRequest);
        String loginReturn = userService.login(loginRequest, user.get());
        return loginReturn;
    }

    @PostMapping("/edit/{user_id}")
    public void updateUser(@PathVariable Long user_id, String password) {
        userService.updateUser(user_id, password);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("user_id") Long user_id) {
        userService.deleteUser(user_id);
    }
}