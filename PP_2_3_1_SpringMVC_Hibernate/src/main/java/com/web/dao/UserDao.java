package com.web.dao;

import com.web.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDao {

    List<User> getAllUsers();
    public void saveUser(User user);
    public User showUserById(int id);
    public void deleteUserById(int id);
}
