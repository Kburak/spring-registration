package com.kburakengin.registerlogin.demo.service;

import com.kburakengin.registerlogin.demo.dto.UserRegistrationDto;
import com.kburakengin.registerlogin.demo.model.User;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
