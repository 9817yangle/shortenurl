package com.conductor.shortenurl.controller;

import com.conductor.shortenurl.entity.Response;
import com.conductor.shortenurl.service.UrlService;
import com.conductor.shortenurl.util.UrlUtil;
import javax.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @author: enping.jep
 * @create: 2023-04-18 2:40 PM
 */
@Controller
public class UrlController {

  @Autowired
  private UrlService urlService;

  private String host;

  @Value("${server.host}")
  public void setHost(String host) {
    this.host = host;
  }

  //默认跳转到首页
  @GetMapping("/")
  public String index() {
    return "index";
  }

  //生成短链接
  @PostMapping("/generate")
  @ResponseBody
  public Response generateShortUrl(@RequestParam String longUrl) {
    if (UrlUtil.checkURL(longUrl)) {
      if (!longUrl.startsWith("http")) {
        longUrl = "http://" + longUrl;
      }
      String shortURL = urlService.generateShortUrl(longUrl);
      return Response.successs("请求成功", host + shortURL);
    }
    return Response.create(400, "URL有误");
  }

  @GetMapping("/{shortURL}")
  public String redirect(@PathVariable String shortURL) {
    String longURL = urlService.getLongUrlByShortUrl(shortURL);
    if (longURL != null) {
      //查询到对应的原始链接，302重定向
      return "redirect:" + longURL;
    }
    //没有对应的原始链接，直接返回首页
    return "redirect:/";
  }

}
