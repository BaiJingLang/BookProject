package com.bai.book.service;

import com.bai.book.bean.User;

public interface UserService {
    public void register(User user);

    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return
     */
    public boolean existsUsername(String username);
}
