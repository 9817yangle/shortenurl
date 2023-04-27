package com.conductor.shortenurl.service;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author: enping.jep
 * @create: 2023-04-27 4:51 PM
 * 布隆过滤器服务
 */
@Service
public class BloomFilterService {

  @Autowired
  private RedissonClient redissonClient;

//  RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("URL").tryInit();

}
