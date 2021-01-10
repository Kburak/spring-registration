package com.kburakengin.registerlogin.demo.service;

import com.kburakengin.registerlogin.demo.dto.UserRegistrationDto;
import com.kburakengin.registerlogin.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    List<User> getAllUsers();
}
