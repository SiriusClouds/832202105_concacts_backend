package com.god.service;

import com.god.entity.Book;
import com.god.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;
    public List<Book> getBookByName(String bookName) {
        return bookMapper.getBookByName(bookName);
    }
    public Book getBookById(Integer bookId) {
        return bookMapper.getBookById(bookId);
    }
    public void update(Book book) {
        bookMapper.update(book);
    }
    public void save(Book book) {
        bookMapper.save(book);
    }
    public List<Book> selectAll() {
        return bookMapper.selectAll();
    }
    public void buy(Integer bookId,Integer quantity ) {
        bookMapper.buy(bookId,quantity);
    }
}
