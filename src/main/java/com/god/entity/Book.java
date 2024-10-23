package com.god.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Book {
    //书名， 出版社， 作者， ISBN码, 类型
    private String bookName, press, author, ISBN, type, isSale;
    //价格, 折扣
    private Double price, discount;
    //数量,上架状态， 编号
    private Integer quantity, bookId;
    //准确时间
    private java.sql.Date date;

    public Book(String bookName, String press, String author, String ISBN, String type, String isSale, Double price, Double discount, Integer quantity, Integer bookId) {
        this.bookName = bookName;
        this.press = press;
        this.author = author;
        this.ISBN = ISBN;
        this.type = type;
        this.isSale = isSale;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.bookId = bookId;
        this.date = new java.sql.Date(System.currentTimeMillis());
    }
}
