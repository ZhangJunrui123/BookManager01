package com.example.BookManager01.utils;

import com.example.BookManager01.model.User;

/**
 * 用来保存当前访问者的容器。我们知道，当web程序运行在web服务器中时，都是并发的
 * 环境，拿tomcat来说，对于每一个请求tomcat都会从自己维护的线程池中选一个线程去处理这个
 * 请求。ThreadLocal这个类提供了一种线程id到一个泛型的绑定，你可以认为它是一个Map，
 * 当我们从里面取数据的时候，实际上是将当前的线程id作为map的key，取出之前这个线程存的
 * 东西。这里我们将User保存在里面，这样我们就能随时在程序的任何地方拿出User信息了。
 */

/**
 * ThreadLocal的作用是提供线程内的局部变量，这种变量在线程的生命周期内起作用，减少同一个线程内多个函数或者组件之间一些公共变量的传递的复杂度。
 * 设计初衷：提供线程内部的局部变量，在本线程内随时随地可取，隔离其他线程。
 * ThreadLocal基本操作：
 * initialValue函数 : initialValue函数用来设置ThreadLocal的初始值,该函数在调用get函数的时候会第一次调用，但是如果一开始就调用了set函数，则该函数不会被调用。
 * get函数：该函数用来获取与当前线程关联的ThreadLocal的值，如果当前线程没有该ThreadLocal的值，则调用initialValue函数获取初始值返回。
 * set函数：  set函数用来设置当前线程的该ThreadLocal的值。
 * remove函数：remove函数用来将当前线程的ThreadLocal绑定的值删除。
 *
 */
public class ConcurrentUtils {
    private static ThreadLocal<User> host = new ThreadLocal<>();

    public static User getHost(){
        return host.get();
    }

    public static void setHost(User user){
        host.set(user);
    }

}
