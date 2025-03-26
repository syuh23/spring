package org.example.jpa_spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpa_spring.dto.request.EditUserRequest;
import org.example.jpa_spring.dto.request.SignInRequest;
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
    public void signUp(SignUpRequest signUpRequest) {

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
    }

    @Transactional
    public void signIn(SignInRequest signInRequest) {

        User user = userRepository.findByEmail(signInRequest.getEmail())
                //TODO. ResponseError Class 만든 후 수정하기
                .orElseThrow(() -> new RuntimeException("user not found"));

        //TODO. ResponseError Class 만든 후 수정하기
        if(!user.getPassword().equals(signInRequest.getPassword())) {
            throw new RuntimeException("wrong password");
        }

        log.info("User signed in {}", user.getName());
    }

    @Transactional
    public String updateUser(EditUserRequest editUserRequest) {

        if(findUserByUserId(editUserRequest.getUserId()).isPresent()) {
            User user = findUserByUserId(editUserRequest.getUserId()).get();

            user.setPassword(editUserRequest.getPassword());
            userRepository.save(user);

            log.info("updated user {}", user.getName());
            return user.getName() + "님의 회원 정보가 변경되었습니다.";

        } else {
            log.info("user not found in updateUser {}", editUserRequest.getUserId());
            return "일치하는 회원 정보를 찾을 수 없습니다.";
        }
    }

    @Transactional
    public String deleteUser(UserIdRequest userIdRequest) {
        if(findUserByUserId(userIdRequest.getUserId()).isPresent()) {
            User user = findUserByUserId(userIdRequest.getUserId()).get();

            user.setUserDeleted(true);
            userRepository.save(user);

            log.info("deleted user {}", user.getName());
            return "회원 탈퇴가 완료되었습니다.";

        } else {
            log.info("user not found in deleteUser {}", userIdRequest.getUserId());
            return "일치하는 회원 정보를 찾을 수 없습니다.";
        }
    }

    @Transactional
    protected Optional<User> findUserByUserId(Long user_id) {
        return userRepository.findById(user_id);
    }
}