package com.god.service;

import com.god.entity.ShoppingItem;
import com.god.mapper.ShoppingItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShoppingItemService {
    @Autowired
    private ShoppingItemMapper shoppingItemMapper;
    public List<ShoppingItem> getList(String account) {
        return shoppingItemMapper.getList(account);
    }

    public void addList(Integer bookId, String account, java.sql.Timestamp timestamp, Integer buyingCount) {
        shoppingItemMapper.addList(bookId,account,timestamp,buyingCount);
    }
    public void deleteList(Integer listId) {
        shoppingItemMapper.deleteList(listId);
    }

    public void updateList(String account) {
        shoppingItemMapper.updateList(account);
    }
    public ShoppingItem getListByListId(Integer listId) {
        return shoppingItemMapper.getListByListId(listId);
    }
}
