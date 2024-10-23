package com.god.controller;

import com.god.entity.Book;
import com.god.entity.Customer;
import com.god.entity.ShoppingItem;
import com.god.service.BookService;
import com.god.service.CustomerService;
import com.god.service.ShoppingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingListController {

    @Autowired
    private ShoppingItemService shoppingItemService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;
    @RequestMapping("/cart")
    public String getAll(Model model, HttpSession session){
        double totalPrice = 0.0;
        String account = session.getAttribute("id").toString();
        //mapper映射不对导致500错误
        List<ShoppingItem> lists = shoppingItemService.getList(account);
        for(ShoppingItem list : lists) {
            if(list.getBook().getDiscount()<1) {
                //特价书打折
                totalPrice += list.getBook().getPrice()*list.getBook().getDiscount()*list.getBuyingCount();
            } else if (list.getCustomer().getIsMember().equals("1")) {
                //会员打9折
                totalPrice += list.getBook().getPrice()*0.9*list.getBuyingCount();
            } else {
                totalPrice += list.getBook().getPrice();
            }
        }
        model.addAttribute("lists",lists);
        model.addAttribute("totalPrice",totalPrice);
        return "cart/list";
    }
    @RequestMapping("/cart/delete/{listId}")
    public String deleteList(@PathVariable Integer listId) {
        shoppingItemService.deleteList(listId);
        return "redirect:/cart";
    }
    //AI神力
    @RequestMapping("/settle/{totalPrice}")
    public String settle(@PathVariable Double totalPrice, HttpSession session, RedirectAttributes redirectAttributes) {
        String account = String.valueOf(session.getAttribute("id"));

        double deposit = customerService.getUser(account).getDeposit()-totalPrice;
        if(deposit < 0) {
            redirectAttributes.addFlashAttribute("msg","账户余额不足");
            return "redirect:/cart";
        }
        customerService.settleMoney(deposit,account);
        shoppingItemService.updateList(account);
        return "redirect:/cart";
    }
    //退货
    @GetMapping("/cart/return/{listId}")
    public String returnBook(@PathVariable Integer listId, Model model) {
        ShoppingItem shoppingItem = shoppingItemService.getListByListId(listId);
        Book book = bookService.getBookById(shoppingItem.getBookId());
        Customer customer = customerService.getUser(shoppingItem.getAccount());
        model.addAttribute("book",book);
        model.addAttribute("shoppingItem",shoppingItem);
        model.addAttribute("customer",customer);
        return "/cart/return";
    }
    @PostMapping("/returnT")
    public String returnT(Integer listId, Integer bookId, Double totalPrice) {
        ShoppingItem shoppingItem = shoppingItemService.getListByListId(listId);
        Book book = shoppingItem.getBook();
        book.setQuantity(book.getQuantity()+shoppingItem.getBuyingCount());
        Customer customer = shoppingItem.getCustomer();
        customer.setDeposit(customer.getDeposit()+totalPrice);

        bookService.update(book);
        shoppingItemService.deleteList(shoppingItem.getListId());
        customerService.update(customer);
        return "redirect:cart";
    }
}
