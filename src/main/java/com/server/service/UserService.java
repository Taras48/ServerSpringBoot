package com.server.service;

import com.server.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> findAll();
    User getUserByName(String name);

}
