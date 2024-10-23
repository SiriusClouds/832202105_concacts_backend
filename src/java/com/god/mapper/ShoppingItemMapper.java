package com.god.mapper;

import com.god.entity.ShoppingItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingItemMapper {
    //1.通过账号查询订单
    List<ShoppingItem> getList(@Param("account")String account);
    //添加购物记录
    void addList(@Param("bookId")Integer bookId,@Param("account")String account,@Param("timestamp")java.sql.Timestamp timestamp, @Param("buyingCount") Integer buyingCount);
    void deleteList(Integer listId);

    void updateList(@Param("account") String account);
    ShoppingItem getListByListId(Integer listId);
}
