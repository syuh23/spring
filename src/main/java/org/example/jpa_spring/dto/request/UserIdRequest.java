package org.example.jpa_spring.dto.request;

import lombok.*;

@Getter
@Builder
public class UserIdRequest {
    // 사실상 필요없는 것 아닐까...
    private Long userId;

}