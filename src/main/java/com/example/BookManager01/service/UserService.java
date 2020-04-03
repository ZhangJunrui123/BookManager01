package com.example.BookManager01.service;

import com.example.BookManager01.dao.UserDAO;
import com.example.BookManager01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserDAO userDAO;

    public int addUser(User user){
        return userDAO.addUser(user);
    }

    public User getUser(String email){
        return  userDAO.selectByEmail(email);
    }

    public User getUser(int id){
        return userDAO.selectById(id);
    }


}
