package com.conductor.shortenurl.service.impl;

import com.conductor.shortenurl.entity.UrlMapping;
import com.conductor.shortenurl.repository.UrlRepository;
import com.conductor.shortenurl.service.UrlService;
import com.conductor.shortenurl.util.HashUtil;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author: enping.jep
 * @create: 2023-04-18 2:29 PM
 */
@Service
public class UrlServiceImpl implements UrlService {

  // 获取日志记录器
  private static Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

  @Autowired
  private UrlRepository urlRepository;

  @Resource
  private RBloomFilter<String> urlBloomFilter;

  @Resource
  RedisTemplate<String, String> redisTemplate;

  //最近使用的短链接缓存过期时间(分钟)
  private static final long TIMEOUT = 10;


  private static final String DUPLICATE = "[DUPLICATE]";

  @Override
  public String generateShortUrl(String longUrl) {

    long hashCode = HashUtil.murmurHashStirng(longUrl);//十进制
    String shortUrl = HashUtil.convertDecToBase62(hashCode);//6位的值
    if (urlBloomFilter.contains(shortUrl)) {
      //不确定是不是已经处理过该URL,查一下缓存，如果缓存中存在则直接返回
      String cacheUrl = redisTemplate.opsForValue().get(shortUrl);
      if (StringUtils.equals(cacheUrl, longUrl)) {
        redisTemplate.expire(shortUrl, TIMEOUT, TimeUnit.MINUTES);
        return shortUrl;
      }
    }
    urlBloomFilter.add(longUrl);
    try {
      urlRepository.saveUrlMapping(new UrlMapping(shortUrl, longUrl));
    } catch (DuplicateKeyException e) {
      logger.info(longUrl + "转化成短链接异常，生成的短链接为：" + shortUrl);
      longUrl += DUPLICATE;
      urlRepository.saveUrlMapping(new UrlMapping(shortUrl, longUrl));
    }
    return shortUrl;
  }

  @Override
  public String generateShortUrl(String longUrl, long timeout, String timeUnit) {
    return null;
  }

  @Override
  public String getLongUrlByShortUrl(String shortUrl) {
    //查找Redis中是否有缓存
    String longURL = redisTemplate.opsForValue().get(shortUrl);
    if (longURL != null) {
      //有缓存，延迟缓存时间
      redisTemplate.expire(shortUrl, TIMEOUT, TimeUnit.MINUTES);
      return longURL;
    }
    //Redis没有缓存，从数据库查找
    longURL = urlRepository.getLongUrlByShortUrl(shortUrl);
    if (longURL != null) {
      //数据库有此短链接，添加缓存
      redisTemplate.opsForValue().set(shortUrl, longURL, TIMEOUT, TimeUnit.MINUTES);
    }
    return longURL;
  }
}
