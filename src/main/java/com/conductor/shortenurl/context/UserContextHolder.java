package com.conductor.shortenurl.context;

/**
 * @author enping
 * @date 2023/4/22 23:00
 **/
public class UserContextHolder {
    public static ThreadLocal<Long> holder = new ThreadLocal<>();
}
