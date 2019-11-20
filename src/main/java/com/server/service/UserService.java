package com.server.service;

import com.spring.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> findAll();

}
