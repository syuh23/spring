package org.example.jpa_spring.dto.request;

import lombok.*;

@Getter
@Builder
public class EditUserRequest {

    private Long userId;

    private String password;

}
