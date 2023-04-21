package com.conductor.shortenurl.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * @author: enping.jep
 * @create: 2023-04-19 2:40 PM
 */
class HashUtilTest {

  private static final String url = "https://dpubstatic.udache.com/static/dpubimg/d4c0e518e95afb4e669affa6eb15d1d6/index.html";


  @Test
  public void murmurHashToBase62() {
    assertTrue("4KpMst".equals(HashUtil.murmurHashToBase62(url)));
  }
}