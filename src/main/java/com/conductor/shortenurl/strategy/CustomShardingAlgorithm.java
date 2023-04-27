package com.conductor.shortenurl.strategy;

import com.conductor.shortenurl.util.HashUtil;
import com.conductor.shortenurl.util.ShardingUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

/*
 * @author: enping.jep
 * @create: 2023-04-27 3:15 PM
 */
public abstract class CustomShardingAlgorithm {

  //计算分片需要
  public long calSlot(PreciseShardingValue<String> preciseShardingValue){
    long hashCode = HashUtil.base62ToLong(preciseShardingValue.getValue());//十进制
    return Math.abs(hashCode % ShardingUtil.SUM_SLOT);//分片序号
  }

}
