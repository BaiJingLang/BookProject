package com.bai.book.service;

import com.bai.book.bean.Book;
import com.bai.book.bean.Page;

import java.util.*;

public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(Integer pageNo, Integer pageSize);

    Page<Book> pageByPrice(Integer pageNo, int pageSize, Integer min, Integer max);
}
