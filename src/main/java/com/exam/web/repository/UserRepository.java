package com.exam.web.repository;

import com.exam.web.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findByName(String nickname);
    UserEntity findFirstByUsername(String username);

}
