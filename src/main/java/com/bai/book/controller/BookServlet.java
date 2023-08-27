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
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();


    //处理分页
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

        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }



    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String bookPrice = request.getParameter("book_price");
        String bookAuthor = request.getParameter("book_author");
        String bookSales = request.getParameter("book_sales");
        String bookStock = request.getParameter("book_stock");

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(bookPrice));

        Book book = new Book(request.getParameter("book_name"),bookAuthor, price,Integer.parseInt(bookSales),Integer.parseInt(bookStock));

        bookService.addBook(book);

        response.sendRedirect(request.getContextPath() + "/book?action=page&pageNo=" + Integer.parseInt(request.getParameter("pageNo")) + 1);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        bookService.deleteBookById(Integer.parseInt(id));

        response.sendRedirect(request.getContextPath() + "/book?action=page");
    }


        protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        String id = request.getParameter("id");
        String bookName = request.getParameter("book_name");
        String bookPrice = request.getParameter("book_price");
        String bookAuthor = request.getParameter("book_author");
        String bookSales = request.getParameter("book_sales");
        String bookStock = request.getParameter("book_stock");

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(bookPrice));

        Book book = new Book(Integer.parseInt(id),bookName,bookAuthor, price,Integer.parseInt(bookSales),Integer.parseInt(bookStock));

        bookService.updateBook(book);

        response.sendRedirect(request.getContextPath() + "/book?action=page");

    }


    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取请求的参数图书编号
        String id = request.getParameter("id");
        //调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(Integer.parseInt(id));
        //保存到图书Request域中
        request.setAttribute("book",book);
        //请求转发到。pages/manager/book_edit.jsp
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        //通过bookService查询全部数据
        List<Book> books = bookService.queryBooks();

        request.setAttribute("bookList",books);

        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
