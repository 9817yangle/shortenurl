package com.conductor.shortenurl.service;

import javax.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 * @author: enping.jep
 * @create: 2023-04-27 4:51 PM
 * 布隆过滤器服务
 */
@Service
public class BloomFilterService {


  @Resource
  private RedissonClient redissonClient;


  private RBloomFilter<String> bloomFilter;

  //构建布隆过滤器，存储短网址的hash值，新建短链接时，判断布隆过滤器中是否已经存在该短网址，不存在则直接新建
  @Autowired
  public RBloomFilter init(RedissonClient redissonClient,
      @Value("${spring.application.bloom}") String bloomName) {
    bloomFilter = redissonClient.getBloomFilter(bloomName);
    bloomFilter.tryInit(100000, 0.03);
    return bloomFilter;
  }



}
