package com.conductor.shortenurl.service.impl;

import com.conductor.shortenurl.entity.UrlMapping;
import com.conductor.shortenurl.repository.UrlRepository;
import com.conductor.shortenurl.service.UrlService;
import com.conductor.shortenurl.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    String shortUrl = HashUtil.murmurHashToBase62(longUrl);
    urlRepository.saveUrlMapping(new UrlMapping(shortUrl, longUrl));
    return shortUrl;
  }

  @Override
  public String generateShortUrl(String longUrl, long timeout, String timeUnit) {
    return null;
  }

  @Override
  public String getLongUrlByShortUrl(String shortUrl) {
    return null;
  }
}
