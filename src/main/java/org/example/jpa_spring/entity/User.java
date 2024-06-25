package org.example.jpa_spring.entity;

import lombok.*;
import jakarta.persistence.*;  //javax -> jakarta 로 이름 변경

// 여기서 내가 class로 정의해주면 JPA가 분석하고 알아서 테이블을 생성해줌 !
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String password;

    public void update(String password) {
        this.password = password;
    }
}