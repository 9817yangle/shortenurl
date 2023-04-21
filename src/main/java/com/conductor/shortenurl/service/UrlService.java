package com.conductor.shortenurl.service;

/*
 * @author: enping.jep
 * @create: 2023-04-18 2:24 PM
 */
public interface UrlService {

  /**
   * 根据长URL生成短URL(不带过期时间)
   *
   * @param longUrl 长Url
   * @return 短Url
   */
  String generateShortUrl(String longUrl);


  /**
   * 根据长URL生成短URL(带过期时间)
   *
   * @param longUrl 长Url
   * @param timeout 超时时间
   * @param timeUnit 时间单位
   * @return 短Url
   */
  String generateShortUrl(String longUrl, long timeout, String timeUnit);


  /**
   * 根据短URL返回长URL
   *
   * @param shortUrl 短Url
   * @return 长URL
   */
  String getLongUrlByShortUrl(String shortUrl);

}
