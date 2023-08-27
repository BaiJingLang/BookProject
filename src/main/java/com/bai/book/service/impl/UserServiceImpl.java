package com.bai.book.service.impl;

import com.bai.book.bean.User;
import com.bai.book.dao.UserDao;
import com.bai.book.dao.impl.UserDaoImpl;
import com.bai.book.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void register(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUserNameAndPassWord(user.getUserName(),user.getPassWord());
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUserName(username) != null;
    }
}
