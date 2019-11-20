package com.server.repository;

import com.server.model.User;

import java.util.List;



public interface UserRepository{

    User getUserById(Long id);
    User getUserByName(String name);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> findAll();

}
