package com.conductor.shortenurl.context;

import java.util.Map;

/**
 * @author enping
 * @date 2023/4/22 23:00
 **/
public class UserContextHolder {

  //存放hashcode的缓存
  public static ThreadLocal<Long> hashCodeHolder = new ThreadLocal<>();

  //存放slot的缓存
  public static ThreadLocal<Long> slotHolder = new ThreadLocal<>();


}
