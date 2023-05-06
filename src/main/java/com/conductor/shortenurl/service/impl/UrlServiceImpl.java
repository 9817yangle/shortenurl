package com.conductor.shortenurl.service.impl;

import com.conductor.shortenurl.entity.UrlMapping;
import com.conductor.shortenurl.repository.UrlRepository;
import com.conductor.shortenurl.service.UrlService;
import com.conductor.shortenurl.util.HashUtil;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/*
 * @author: enping.jep
 * @create: 2023-04-18 2:29 PM
 */
@Service
public class UrlServiceImpl implements UrlService {

  @Autowired
  private UrlRepository urlRepository;

  @Resource
  private RBloomFilter<String> urlBloomFilter;

  @Resource
  RedisTemplate<String, String> redisTemplate;

  //最近使用的短链接缓存过期时间(分钟)
  private static final long TIMEOUT = 10;


  private static final String DUPLICATE = "*";

  @Override
  public String generateShortUrl(String longUrl) {
    long hashCode = HashUtil.murmurHashStirng(longUrl);//十进制
    String shortUrl = HashUtil.convertDecToBase62(hashCode);//6位的值
    //当长 URL 转换短链的请求打过来的时候，先判断布隆过滤器里有没有这个 URL， 如果没有，
    // 那么说明这个长 URL 还没有转换过，接下来短链服务就给它生成一个对应的短链。
    // 如果布隆过滤器判断该 URL 已经存在了，那么就不再给它分配新的自增 ID。
    try {
      urlRepository.saveUrlMapping(new UrlMapping(shortUrl, longUrl));
    } catch (DuplicateKeyException e) {
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
