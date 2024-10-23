package com.god.controller;

import com.god.entity.Customer;
import com.god.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("/info")
    public String getUser(Model model,HttpSession session) {
        Customer info = customerService.getUser((String)session.getAttribute("id"));
        if(session.getAttribute("user") == "admin") {
            info.setIsMember("1");
        }
        customerService.update(info);
        model.addAttribute("info",info);
        return "info/list";
    }
    //修改信息
    @GetMapping("/info/{account}")
    public String update(@PathVariable String account, Model model) {
        Customer customer = customerService.getUser(account);
        model.addAttribute("customer",customer);
        return "info/update";
    }
    @PostMapping("/updateInfo")
    public String updateInfo(Customer customer) {
        customerService.update(customer);
        return "redirect:/info";
    }
    //充值
    @GetMapping("/recharge/{account}")
    public String recharge(@PathVariable String account, Model model) {
        Customer customer = customerService.getUser(account);
        model.addAttribute("customer",customer);
        return "/info/recharge";
    }
    @PostMapping("/rechargeT")
    public String rechargeT(Customer customer, Double money,Model model) {
        if(money == null) {
            model.addAttribute("msg","充值金额不能为空");
            return "/info/recharge";
        }
        double t = (customer.getDeposit()==null?0:customer.getDeposit())+money;
        if(t > 10000000) {
            customer.setIsMember("1");
        }
        customer.setDeposit(t);
        customerService.recharge(customer);
        return "redirect:info";
    }
}
