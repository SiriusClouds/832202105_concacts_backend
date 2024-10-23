package com.god.controller;


import com.god.entity.Book;
import com.god.service.BookService;
import com.god.service.ShoppingItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    //通过id得到用户信息
    @Autowired
    private ShoppingItemService shoppingItemService;

    //进货
    @GetMapping("/book/add")
    public String add() {
        return "/book/add";
    }
    @PostMapping("/addBook")
    public String addBook(Book book) {
        book.setDate(new java.sql.Date(System.currentTimeMillis()));
        bookService.save(book);
        return "redirect:/books";
    }
    //展示书籍信息
    @RequestMapping("/books")
    public String getAll(Model model){
        List<Book> books = bookService.selectAll();
        for (Book book : books) {
            if (ChronoUnit.DAYS.between(book.getDate().toLocalDate(), LocalDate.now()) > 200) {
                book.setDiscount(0.8);
                bookService.update(book);
            }
        }
        model.addAttribute("books", books);
        return "book/list";
    }
    //修改书籍信息
    @GetMapping("/book/{id}")
    public String update(@PathVariable Integer id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "book/update";
    }
    @PostMapping("/updateBook")
    public String updateBook(Book book) {
        bookService.update(book);
        return "redirect:/books";
    }
    //查询书籍页面
    @RequestMapping("/getBook")
    public String getBook(String bookName, Model model) {
        List<Book> books =  bookService.getBookByName(bookName);
        model.addAttribute("books", books);
        return "book/search";
    }
    //购买页面
    @GetMapping("/book/buy/{id}")
    public String buy(@PathVariable Integer id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "book/buy";
    }
    @PostMapping("/buyBook")
    public String buyBook(Book book, Integer buyingCount, Model model, HttpSession session) {
        int quantity = book.getQuantity()-buyingCount;
        if(quantity<0) {
            model.addAttribute("msg","库存不足！");
            return "book/buy";
        } else {
            //参数不要传错类型, 改变函数的原因是html传参是没有参数默认为null所以isSale 会丢失
            bookService.buy(book.getBookId(),quantity);
            shoppingItemService.addList(book.getBookId(), String.valueOf(session.getAttribute("id")),new Timestamp(System.currentTimeMillis()),buyingCount);
            return "redirect:/cart";
        }
    }
}
