package com.bai.book.dao;

import com.bai.book.bean.Book;

import java.util.*;

public interface BookDao {
    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, Integer pageSize);

    Integer queryForPageTotalCountByPrice(Integer min, Integer max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, Integer min, Integer max);

}
