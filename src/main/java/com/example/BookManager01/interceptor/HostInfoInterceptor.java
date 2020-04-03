package com.example.BookManager01.interceptor;

import com.example.BookManager01.model.Ticket;
import com.example.BookManager01.model.User;
import com.example.BookManager01.service.TicketService;
import com.example.BookManager01.service.UserService;
import com.example.BookManager01.utils.ConcurrentUtils;
import com.example.BookManager01.utils.CookieUtils;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)
        throws Exception{
        String t = CookieUtils.getCookie("t",request);
        if(!StringUtils.isEmpty(t)){
            Ticket ticket = ticketService.getTicket(t);
            if(ticket != null && ticket.getExpiredAt().after(new Date())){
                User host = userService.getUser(ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }
}
