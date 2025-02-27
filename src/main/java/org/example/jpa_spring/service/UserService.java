package org.example.jpa_spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.entity.User;
import org.example.jpa_spring.dto.request.FormUser;
import org.example.jpa_spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(FormUser formUser) {
        User createUser = User.builder()
                .name(formUser.getName())
                .password(formUser.getPassword())
                .build();
        userRepository.save(createUser);
    }

    @Transactional
    public Optional<User> findUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
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