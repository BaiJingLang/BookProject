package com.bai.book.controller;

import com.bai.book.bean.User;
import com.bai.book.service.UserService;
import com.bai.book.service.impl.UserServiceImpl;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet("/user")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        User loginUser = userService.login(user);

        if (loginUser == null) {
            //把错误信息和回显的表单项信息，保存到Request域中
            request.setAttribute("msg", "用户名或密码错误！");
            request.setAttribute("username", username);

            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("user",loginUser);

            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取session中的验证码
        String  token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if (token!=null && token.equals(code)) {
            if (userService.existsUsername(username)) {
                System.out.println("用户名：" + username + "不可用");

                request.setAttribute("msg", "用户名已存在！！");
                request.setAttribute("username", username);
                request.setAttribute("email", email);

                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                User user = new User();
                user.setUserName(username);
                user.setPassWord(password);
                user.setEmail(email);
                userService.register(user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "验证码错误！！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);

            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }
}
