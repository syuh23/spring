package org.example.jpa_spring.form;

import lombok.*;

// form data : HTML 문서의 form 태그에 담긴 데이터
// DTO를 보고 함
@Getter
@Builder
public class FormUser {
    private String name;
    private String password;


//    public FormUser(String name, String password) {
//        this.name = name;
//        this.password = password;
//    }
}
