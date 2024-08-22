package com.exam.web.service;

import com.exam.web.dto.RegistrationDto;
import com.exam.web.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByName(String name);
    boolean checkPassword(UserEntity user, String rawPassword);
    UserEntity loadUserByUsername(String username);

}
