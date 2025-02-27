package org.example.jpa_spring.dto.request;

import lombok.*;

@Getter
@Builder
public class FormUser {
    private String name;
    private String password;
}
