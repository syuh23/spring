package org.example.jpa_spring.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EditUserRequest {
    private Long user_id;
    private String password;
}
