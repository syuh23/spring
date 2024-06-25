package org.example.jpa_spring.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.jpa_spring.entity.User;
import org.example.jpa_spring.form.EditUser;
import org.example.jpa_spring.form.FormUser;
import org.example.jpa_spring.repository.UserRepository;
import org.example.jpa_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController //RESTFUL API 형식을 지키기 위해 사용하는 어노테이션 // controller라고 명세
//@RequestMapping(headers = {"Content-type=application/json"}) // -> 공통되는 url인 경우에는 class 밖에
//@ResponseBody // -> ??
@RequiredArgsConstructor //알아보기!!!!!! 일단 에러나면 얘 넣어보고 빨간줄 뜨면 No어쩌고 넣고 안되면 둘다 넣기
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;  //뭔가를 가져오는걸 한다? 하면 무조건 final

    // UserService에 의존성을 주입하는 부분..... -> 얘 때문에 한참 걸림 ㅠㅠ
//    @Autowired
//    public UserController(UserService userService, UserRepository userRepository) {
//        this.userService = userService;
//        this.userRepository = userRepository;
//    }

    @PostMapping("/signup") //-> 성공....
    public void createUser(@RequestBody FormUser formuser) {
        //System.out.println(formuser);
        userService.createUser(formuser);
    }

    @GetMapping("/find") //-> 빠르게 성공 !
    public Optional<User> findUser(@RequestParam("user_id") Long user_id) {  //RequestParam : Get 요청은 RequestBody를 지원하지 않음!!
        Optional<User> user = userService.findUser(user_id);
        return user;
    }

    @PostMapping("/edit/{user_id}") //-> 성공...!!
    public void updateUser(@PathVariable Long user_id, String password) {  //postman에서 실행할 때 꼭 @RequestParam / @RequestBody 없어도 됨, form-data로도 실행 가능 !
        userService.updateUser(user_id, password);
    }

    @DeleteMapping("/delete") //-> 성공 !
    public void deleteUser(@RequestParam("user_id") Long user_id) {
        userService.deleteUser(user_id);
    }
}