package com.kburakengin.registerlogin.demo.service;

import com.kburakengin.registerlogin.demo.dto.UserRegistrationDto;
import com.kburakengin.registerlogin.demo.model.Role;
import com.kburakengin.registerlogin.demo.model.User;
import com.kburakengin.registerlogin.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(),
                            registrationDto.getEmail(), registrationDto.getPassword(), Arrays.asList(new Role("ROLE USER")));

        return userRepository.save(user);
    }
}
