package org.example.jpa_spring.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseEnum {

    // 200
    SUCCESS(HttpStatus.OK, "성공"),

    // 409
    ALREADY_EXISTS_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");

    private final HttpStatus httpStatus;
    //private final int code;  ->  과연 필요할까 ?
    private final String message;
}
