package org.example.jpa_spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa_spring.dto.request.EditUserRequest;
import org.example.jpa_spring.dto.request.LoginRequest;
import org.example.jpa_spring.dto.request.UserIdRequest;
import org.example.jpa_spring.entity.User;
import org.example.jpa_spring.dto.request.SignUpRequest;
import org.example.jpa_spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String signUp(SignUpRequest signUpRequest) {

        if(userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            log.info("duplicate email address {}", signUpRequest.getEmail());
        }

        User createUser = User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build();
        userRepository.save(createUser);

        log.info("created user {}", signUpRequest.getEmail());

        return "User created : " + signUpRequest.getName() + "님";
    }

    @Transactional
    public String signIn(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                //TODO. ResponseError Class 만든 후 수정하기
                .orElseThrow(() -> new RuntimeException("user not found"));

        //TODO. ResponseError Class 만든 후 수정하기
        if(!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("wrong password");
        }

        log.info("User signed in {}", user.getName());

        return "User signed in : " + loginRequest.getEmail();
    }

    @Transactional
    public String updateUser(EditUserRequest editUserRequest) {

        if(findUserByUserId(editUserRequest.getUser_id()).isPresent()) {
            User user = findUserByUserId(editUserRequest.getUser_id()).get();
            user.setPassword(editUserRequest.getPassword());
            userRepository.save(user);
            return "User updated : " + user.getName();
        } else {
            return "User not found : " + editUserRequest.getUser_id();
        }
    }

    @Transactional
    public String deleteUser(UserIdRequest userIdRequest) {
        if(findUserByUserId(userIdRequest.getUser_id()).isPresent()) {
            User user = findUserByUserId(userIdRequest.getUser_id()).get();
            user.setUserDeleted(true);
            userRepository.save(user);
            return "User deleted update : " + user.getName();
        } else {
            return "User not found : " + userIdRequest.getUser_id();
        }
    }

//    @Transactional
//    protected boolean duplicateEmail(SignUpRequest signUpRequest) {
//        return userRepository.findByEmail(signUpRequest.getEmail()).isPresent();
//    }

    @Transactional
    public Optional<User> findUserByEmail(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        return user;
    }

    @Transactional
    protected Optional<User> findUserByUserId(Long user_id) {
        return userRepository.findById(user_id);
    }
}