package com.conductor.shortenurl.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @author: enping.jep
 * @create: 2023-04-28 4:36 PM
 */
@Configuration
public class UrlBloomFilterConfig {

  @Bean
  public RBloomFilter<String> urlBloomFilter(RedissonClient redissonClient) {
    RBloomFilter<String> urlBloomFilter = redissonClient.getBloomFilter("URL");
    urlBloomFilter.tryInit(10000000L, 0.01);
    return urlBloomFilter;
  }

}
