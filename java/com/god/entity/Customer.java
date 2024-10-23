package com.god.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Random;

@Data
@NoArgsConstructor
public class Customer {
    //账号，姓名，电话号码，存款， 密码
    private String account, username, telephone, password, address, isMember;
    private Double deposit;
    //是否为会员
    //注册时间
    private java.sql.Timestamp timestamp;
    public String createAccount() {
        Random random = new Random();
        StringBuilder result= new StringBuilder();
        for (int i=0;i<6;i++)
        {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
    public Customer(String username, String telephone,String address, Double deposit, String password) {
        this.account = createAccount();
        this.username = username;
        this.telephone = telephone;
        this.address = address;
        this.deposit = deposit;
        this.password = password;
        this.isMember = "0";
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
