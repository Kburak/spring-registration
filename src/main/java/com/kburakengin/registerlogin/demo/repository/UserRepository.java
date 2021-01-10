package com.kburakengin.registerlogin.demo.repository;

import com.kburakengin.registerlogin.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
