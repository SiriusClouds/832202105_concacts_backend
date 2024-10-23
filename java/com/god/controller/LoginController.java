package com.god.controller;

import com.god.entity.Customer;
import com.god.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller //自动映射URL，支持视图解析
public class LoginController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("/user/login")
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        Customer customer = customerService.getUser(account);
        if(customer == null) {
            model.addAttribute("msg","用户不存在！");
            return "index";
        } else if(customer.getPassword().equals(password)) {
            session.setAttribute("loginUser",customer.getUsername());
            session.setAttribute("id",customer.getAccount());
            if(account.equals("admin")) {
                session.setAttribute("user","admin");
            } else {
                session.setAttribute("user","user");
            }
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg","密码错误！");
            return "index";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
