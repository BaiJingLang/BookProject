package com.bai.book.dao.impl;

import com.bai.book.bean.User;
import com.bai.book.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUserName(String userName) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return queryForOne(User.class,sql,userName);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values (?,?,?)";
        return update(sql,user.getUserName(),user.getPassWord(),user.getEmail());
    }

    @Override
    public User queryUserByUserNameAndPassWord(String userName, String passWord) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,userName,passWord);
    }
}
