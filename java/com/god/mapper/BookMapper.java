package com.god.mapper;

import com.god.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    //通过编号查找书
    Book getBookById(Integer bookId);
    //1.通过书名查询
    List<Book> getBookByName(String bookName);
    //3.更改用户信息
    void update(Book book);
    //4.插入用户信息
    void save(Book book);
    //5.查询所有用户信息
    List<Book> selectAll();
    //购买 加上@Param 方便识别参数
    void buy(@Param("bookId")Integer bookId, @Param("quantity")Integer quantity);

}
