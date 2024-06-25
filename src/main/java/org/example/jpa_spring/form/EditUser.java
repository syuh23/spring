package org.example.jpa_spring.form;

import lombok.*;

@Getter
@Builder
@Data
public class EditUser {
    private String password;

//    public EditUser() {} //오류 떠서 추가함... -> 왜 ??
//
//    public EditUser(String password) {
//        this.password = password;
//    }
}
