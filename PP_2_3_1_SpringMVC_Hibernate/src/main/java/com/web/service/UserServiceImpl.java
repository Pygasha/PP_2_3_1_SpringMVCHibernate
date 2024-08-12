package com.web.service;

import com.web.dao.UserDao;
import com.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User showUserById(int id) {
        return userDao.showUserById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

}

