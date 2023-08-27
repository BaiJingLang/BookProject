package com.bai.book.service.impl;

import com.bai.book.bean.Book;
import com.bai.book.bean.Page;
import com.bai.book.dao.BookDao;
import com.bai.book.dao.impl.BookDaoImpl;
import com.bai.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();


        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount / pageSize > 0){
            pageTotal ++;
        }

        if (pageNo < 1){
            pageNo = 1;
        }
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);

        page.setPageTotal(pageTotal);

        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, int pageSize, Integer min, Integer max) {
        Page<Book> page = new Page<>();


        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount / pageSize > 0){
            pageTotal ++;
        }

        if (pageNo < 1){
            pageNo = 1;
        }
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);

        page.setPageTotal(pageTotal);

        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
