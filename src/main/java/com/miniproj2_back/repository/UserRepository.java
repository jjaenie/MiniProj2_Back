package com.miniproj2_back.repository;

import com.miniproj2_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    void deleteById(int id);
    User findByEmail(String email);
}