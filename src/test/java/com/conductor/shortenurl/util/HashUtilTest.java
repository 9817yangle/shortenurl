package com.conductor.shortenurl.util;

import static org.junit.jupiter.api.Assertions.*;

import com.conductor.shortenurl.context.UserContextHolder;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

/*
 * @author: enping.jep
 * @create: 2023-04-19 2:40 PM
 */
class HashUtilTest {

  private static final String url = "https://dpubstatic.udache.com/static/dpubimg/d4c0e518e95afb4e669affa6eb15d1d6/index.html";


  @Test
  public void murmurHashToBase62() {
    long hashCode = HashUtil.murmurHashStirng(url);
    assertTrue("4KpMst".equals(HashUtil.convertDecToBase62(hashCode)));
    long value = 100L << 26;
    System.out.println(value);
  }

  @Test
  public void generateUrl() {
    Faker faker = new Faker();

    System.out.println(faker.internet().url() + "/" + faker.number().randomNumber());
  }
}