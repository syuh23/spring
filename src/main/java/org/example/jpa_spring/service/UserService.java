package org.example.jpa_spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.entity.User;
import org.example.jpa_spring.form.EditUser;
import org.example.jpa_spring.form.FormUser;
import org.example.jpa_spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //user data 생성
    @Transactional
    public void createUser(FormUser formunser) {
        User createUser = User.builder()
                .name(formunser.getName())
                .password(formunser.getPassword())
                .build();
        userRepository.save(createUser);
    }

    //where id = 1
    @Transactional
    public Optional<User> findUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    //update user data
    @Transactional  //얘가 하나의 코드 뭉탱이를 실패 없이 모두 실행시켜주는데, 만약 오류가 나면 아예 끝나버림
    //user 0, 1, 2, 3을 save 하는 함수를 transactional 됐을 때 0을 성공하고 1을 실패했다면 0도 저장되지 않음
    public void updateUser(Long user_id, String password) {
        User user = userRepository.findById(user_id).orElseThrow(()->
                new IllegalArgumentException("해당 user_id가 없습니다 : " + user_id));
        user.update(password);
        userRepository.save(user);
        // 원래는 이 뒤에 save까지 해줘야 하는데... transactional 때문에 그냥 다 된듯...!!!! -> 일단 완벽한 코드는 아님 ㄷㅏ시 공부해보기
        // update 메소드 -> user
    }

    //delete -> isDelete로 수정하긴 함 보통 !!
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
//@SpringBootTest -> Test 폴더에서 쓰는 것
//public class UserService {
//
//    @Autowired -> 빈 충돌 가능성!! 억지로 빈을 주입해주는 친구... 되도록이면 쓰지 말자
//     UserRepository userRepository;
//
//    @Test
//    void postUser() {
//        User user = User.builder().name("seunghee").build();
//        userRepository.save(user);
//    }
//
//    @Test
//    void userFind() {
//        Optional<User> user = userRepository.findById(1L);
//        System.out.println(user.isPresent() ? user.get().toString() : "Noting!!");
//    }
//
//    @Test
//    void deleteUser() {
//        userRepository.deleteById(1L);
//    }
//
//}
