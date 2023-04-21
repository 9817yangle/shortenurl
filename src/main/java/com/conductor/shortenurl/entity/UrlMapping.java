package com.conductor.shortenurl.entity;

import java.util.Date;

/*
 * @author: enping.jep
 * @create: 2023-04-18 7:37 PM
 */
public class UrlMapping {

  private Long id;

  private String shortUrl;

  private String longUrl;

  private Date expireTime;


  public UrlMapping(String shortUrl, String longUrl) {
    this.shortUrl = shortUrl;
    this.longUrl = longUrl;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  public Date getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Date expireTime) {
    this.expireTime = expireTime;
  }
}
