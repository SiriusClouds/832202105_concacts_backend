package com.god.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
public class ShoppingItem {
    //这是一个记录，多个组成订单
    private Integer listId, buyingCount, bookId, isBuy;
    private String account;
    private java.sql.Timestamp timestamp;
    private Book book;
    private Customer customer;

    public ShoppingItem(Integer listId, Integer buyingCount, Integer bookId, Integer isBuy, String account, Book book, Customer customer) {
        this.listId = listId;
        this.buyingCount = buyingCount;
        this.bookId = bookId;
        this.account = account;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.book = book;
        this.customer = customer;
        this.isBuy = 0;
    }
}
