package org.example.jpa_spring;

import org.example.jpa_spring.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Test
    @DisplayName("회원가입 - User가 잘 생성되는가")
    void createUser() {
        // given
        User user = User.builder()
                .name("sh")
                .email("abc@naver.com")
                .password("123")
                .build();


    }
}
