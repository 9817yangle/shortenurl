package com.conductor.shortenurl.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.conductor.shortenurl.entity.UrlMapping;
import com.conductor.shortenurl.repository.UrlRepository;
import com.conductor.shortenurl.service.UrlService;
import com.conductor.shortenurl.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/*
 * @author: enping.jep
 * @create: 2023-04-18 2:29 PM
 */
@Service
public class UrlServiceImpl implements UrlService {

  @Autowired
  UrlRepository urlRepository;


  @Override
  public String generateShortUrl(String longUrl) {
    long hashCode = HashUtil.murmurHashStirng(longUrl);//十进制
    String shortUrl = HashUtil.convertDecToBase62(hashCode);//6位的值
    urlRepository.saveUrlMapping(new UrlMapping(shortUrl, longUrl));
    return shortUrl;
  }

  @Override
  public String generateShortUrl(String longUrl, long timeout, String timeUnit) {
    return null;
  }

  @Override
  public String getLongUrlByShortUrl(String shortUrl) {
    return urlRepository.getLongUrlByShortUrl(shortUrl);
  }


}
