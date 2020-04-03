package com.example.BookManager01.service;

import com.example.BookManager01.dao.BookDAO;
import com.example.BookManager01.model.Book;
import com.example.BookManager01.model.enums.BookStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired(required = false)
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.selectAll();
    }

    public int addBooks(Book book) {
        return bookDAO.addBook(book);
    }

    public void deleteBooks(int id) {
        bookDAO.updateBookStatus(id, BookStatusEnum.DELETE.getValue());
    }

    public void recoverBooks(int id) {
        bookDAO.updateBookStatus(id, BookStatusEnum.NORMAL.getValue());
    }
}
