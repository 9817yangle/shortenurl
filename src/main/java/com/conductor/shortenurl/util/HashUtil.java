package com.conductor.shortenurl.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

/*
 * @author: enping.jep
 * @create: 2023-04-18 3:47 PM
 */
public class HashUtil {

  private static final char[] CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
      'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
      'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  private static final int SIZE = CHARS.length;

  /**
   * 将数字转换成62进制
   */
  private static String convertDecToBase62(long num) {
    StringBuilder sb = new StringBuilder();
    while (num > 0) {
      int i = (int) (num % SIZE);
      sb.append(CHARS[i]);
      num /= SIZE;
    }
    return sb.reverse().toString();
  }

  /**
   * 用MurmurHash算法计算字符串的hash值,再将该hash值转成62进制
   */
  public static String murmurHashToBase62(String str) {
    long hashValue = Hashing.murmur3_32_fixed().hashString(str, StandardCharsets.UTF_8).padToLong();
    return convertDecToBase62(hashValue);


  }
}
