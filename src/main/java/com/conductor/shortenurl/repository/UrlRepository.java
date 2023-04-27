package com.conductor.shortenurl.repository;

import com.conductor.shortenurl.entity.UrlMapping;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
 * @author: enping.jep
 * @create: 2023-04-18 6:47 PM
 */
@Repository
@Mapper
public interface UrlRepository {

  int saveUrlMapping(UrlMapping urlMapping);

  String getLongUrlByShortUrl(String shortUrl);
}
