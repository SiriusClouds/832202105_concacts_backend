package com.god.controller;


import com.god.entity.Customer;
import com.god.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class RegisterController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/user/register")
    public String insert(@RequestParam("username") String username,
                         @RequestParam("telephone") String telephone,
                         @RequestParam("address") String address,
                         @RequestParam(required = false, name = "deposit") Double deposit, //允许空值传入
                         @RequestParam("password") String password,
                         Model model) {
        //System.out.println(username);
        if(deposit == null) {
            deposit = 0.0;
        }
        Customer customer = new Customer(username,telephone,address,deposit,password);
        customerService.save(customer);
        model.addAttribute("account",customer.getAccount());
        model.addAttribute("password",customer.getPassword());
        return "index";
    }
}
