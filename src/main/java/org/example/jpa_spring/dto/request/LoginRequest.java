package org.example.jpa_spring.dto.request;

import lombok.*;

@Getter
@Builder
public class LoginRequest {

    private String email;

    private String password;

}
