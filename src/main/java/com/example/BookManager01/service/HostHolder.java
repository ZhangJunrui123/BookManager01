package com.example.BookManager01.service;

import com.example.BookManager01.model.User;
import com.example.BookManager01.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {
    public User getUser(){
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user){
        ConcurrentUtils.setHost(user);
    }
}
