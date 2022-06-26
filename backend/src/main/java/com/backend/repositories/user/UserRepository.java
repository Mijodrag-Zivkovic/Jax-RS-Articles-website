package com.backend.repositories.user;

import com.backend.entities.User;

import java.util.List;

public interface UserRepository {
    
    public List<User> allUsers();

    public User addUser(User user);

    public User findUser(Integer id);

    public User findUser(String email);

    public void deleteUser(Integer id);

    public User updateUser(Integer id, User user);
}
