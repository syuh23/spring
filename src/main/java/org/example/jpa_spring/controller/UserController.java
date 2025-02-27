package org.example.jpa_spring.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.entity.User;
import org.example.jpa_spring.dto.request.FormUser;
import org.example.jpa_spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public void createUser(@RequestBody FormUser formUser) {
        userService.createUser(formUser);
    }

    @GetMapping("/find")
    public Optional<User> findUser(@RequestParam("user_id") Long user_id) {
        Optional<User> user = userService.findUser(user_id);
        return user;
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