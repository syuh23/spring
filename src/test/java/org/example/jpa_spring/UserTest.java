//package org.example.jpa_spring;
//
//import org.example.jpa_spring.dto.request.SignUpRequest;
//import org.example.jpa_spring.entity.User;
//import org.example.jpa_spring.repository.UserRepository;
//import org.example.jpa_spring.service.UserService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class UserTest {
//
//    @MockBean  // 가짜 UserRepository 객체 생성, 가짜로 Bean 주입하여 실제로 DB에 저장되는 것처럼 보이게 해줌.
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    @DisplayName("회원가입 - 성공, User 객체가 잘 생성되는가")
//    void signUpTest() {
//        // Given -> Test를 위한 준비
//        SignUpRequest request = new SignUpRequest("sh", "abc@naver.com", "123");
//        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
//        when(userRepository.save(any(User.class))).thenReturn(null);
//
//        // When -> 실제 Test, 여기서는 DB에 User 객체가 잘 저장되는지 테스트
//        String result = userService.signUp(request);
//
//        // Then -> Test 결과 검증
//        assertEquals("User created : sh님", result);
//
//    }
//
//    @Test
//    @DisplayName("회원가입 - 실패, 중복된 Email")
//    void signUpDuplicateEmailTest() {
//
//    }
//}
