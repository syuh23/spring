package org.example.jpa_spring.repository;
import org.example.jpa_spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 여기에 정의할 친구들은 DIY 함수 같은 느낌 !!
}
