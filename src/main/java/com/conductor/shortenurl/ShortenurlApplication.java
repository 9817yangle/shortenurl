package com.conductor.shortenurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
public class ShortenurlApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShortenurlApplication.class, args);
  }

}
