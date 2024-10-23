package com.god.service;

import com.god.entity.Customer;
import com.god.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    public Customer getUser(String account) {
        return customerMapper.getUser(account);
    }
    public void save(Customer customer) {
        customerMapper.save(customer);
    }
    public List<Customer> selectAll() {
        return customerMapper.selectAll();
    }

    public void update(Customer customer) {
        customerMapper.update(customer);
    }

    public void recharge(Customer customer) {
        customerMapper.recharge(customer);
    }

    public void settleMoney(Double deposit, String account) {
        customerMapper.settleMoney(deposit, account);
    }
}

