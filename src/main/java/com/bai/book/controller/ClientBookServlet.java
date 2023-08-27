package com.bai.book.controller;

import com.bai.book.bean.Book;
import com.bai.book.bean.Page;
import com.bai.book.service.BookService;
import com.bai.book.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/bookServlet")
public class ClientBookServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        Integer MIN;
        Integer MAX;
        if (min == null){
            MIN = 0;
        }else {
            MIN = Integer.parseInt(min);
        }
        if (max == null){
            MAX = Integer.MAX_VALUE;
        }else {
            MAX = Integer.parseInt(max);
        }

        //获取请求的参数pageNo和pageSize
        String No = request.getParameter("pageNo");
        Integer pageNo;
        if (No == null){
            pageNo = 1;
        }else {
            pageNo = Integer.parseInt(No);
        }
        //调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.pageByPrice(pageNo,4,MIN,MAX);

        //保存page对象到Request域中
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }

        protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数pageNo和pageSize
        String No = request.getParameter("pageNo");
        Integer pageNo;
        if (No == null){
            pageNo = 1;
        }else {
            pageNo = Integer.parseInt(No);
        }
        //调用BookService.page(pageNo,pageSize)
        Page<Book> page = bookService.page(pageNo,4);

        //保存page对象到Request域中
        request.setAttribute("page",page);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
