package org.example.jpa_spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.dto.request.LoginRequest;
import org.example.jpa_spring.entity.User;
import org.example.jpa_spring.dto.request.SignUpRequest;
import org.example.jpa_spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        User createUser = User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build();
        userRepository.save(createUser);
    }

    @Transactional
    public Optional<User> findUser(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        return user;
    }

    @Transactional
    public String login(LoginRequest loginRequest, User findUser) {
        if (loginRequest.getPassword().equals(findUser.getPassword())) {
            return "Successfully logged in : " + findUser.getName();
        } else
            return "Wrong password";
    }

    @Transactional
    public void updateUser(Long user_id, String password) {
        User user = userRepository.findById(user_id).orElseThrow(()->
                new IllegalArgumentException("해당 user_id가 없습니다 : " + user_id));
        user.setPassword(password);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}