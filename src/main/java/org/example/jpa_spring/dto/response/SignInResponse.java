package org.example.jpa_spring.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {
    //TODO. 추후 수정 예정
    private String token;
    private String name;
}
