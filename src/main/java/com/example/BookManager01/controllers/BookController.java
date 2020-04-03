package com.example.BookManager01.controllers;

import com.example.BookManager01.model.Book;
import com.example.BookManager01.model.User;
import com.example.BookManager01.service.BookService;
import com.example.BookManager01.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model) {

        User host = hostHolder.getUser();
        if (host != null) {
            model.addAttribute("host", host);
        }
        loadAllBooksView(model);
        return "book/books";

    }
//到"/books/add"的请求会由addBook（）来处理
    @RequestMapping(path = {"/books/add"}, method = {RequestMethod.GET})
    public String addBook() {
        return "book/addbook";
    }

    /**
     * @requesparam方法注解使用的时候可以有一个值，也可以没有值。这个值指定了需要被映射到处理方法
     * 参数的请求参数,为了能降一个请求映射到一个特定的 HTTP 方法，你需要在 @RequestMapping 中使用
     * method 来声明 HTTP 请求所使用的方法类型
     * @RequestMapping 中使用 method 来声明 HTTP 请求所使用的方法类型
     * @param name
     * @param author
     * @param price
     * @return
     */
    @RequestMapping(path = {"/books/add/do"}, method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") String price
    ) {

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.addBooks(book);

        return "redirect:/index";

    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})
    public String deleteBook(
            @PathVariable("bookId") int bookId
    ) {

        bookService.deleteBooks(bookId);
        return "redirect:/index";

    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
    public String recoverBook(
            @PathVariable("bookId") int bookId
    ) {

        bookService.recoverBooks(bookId);
        return "redirect:/index";

    }

    /**
     * 为model加载所有的book
     */

    //往前台传数据，可以传对象，可以传List，通过el表达式 ${}可以获取到
    private void loadAllBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }

}

