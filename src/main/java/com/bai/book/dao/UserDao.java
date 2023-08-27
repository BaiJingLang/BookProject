package com.bai.book.dao;

import com.bai.book.bean.User;

public interface UserDao{


    /**
     * 根据用户名查询用户信息
     */
    public User queryUserByUserName(String userName);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户
     */
    public User queryUserByUserNameAndPassWord(String userName,String passWord);
}
