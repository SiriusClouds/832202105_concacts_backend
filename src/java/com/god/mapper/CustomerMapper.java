package com.god.mapper;

import com.god.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //将类识别为Bean
public interface CustomerMapper {
    //1.通过账号查询
    Customer getUser(String account);
    //3.更改用户信息
    void update(Customer customer);
    //4.插入用户信息
    void save(Customer customer);
    //显示用户信息
    List<Customer> selectAll();

    void recharge(Customer customer);


    //要是能重来，我要全部加上@Param
    void settleMoney(@Param("deposit") Double deposit, @Param("account") String account);
}
