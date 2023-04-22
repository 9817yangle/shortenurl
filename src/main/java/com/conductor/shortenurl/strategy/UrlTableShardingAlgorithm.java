package com.conductor.shortenurl.strategy;

import com.conductor.shortenurl.context.UserContextHolder;
import com.conductor.shortenurl.util.HashUtil;
import com.conductor.shortenurl.util.ShardingUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author enping
 * @date 2023/4/22 20:36
 **/
public class UrlTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> preciseShardingValue) {
        long hashCode = UserContextHolder.holder.get();//获得hash
        long slot = Math.abs(hashCode % ShardingUtil.SUM_SLOT);//分片序号
        try {
            for (String tableName : tableNames) {
                if (tableName.endsWith(String.valueOf(slot % ShardingUtil.TBL_CNT))) {
                    return tableName;
                }
            }
            return null;
        } finally {
            UserContextHolder.holder.remove();
        }
    }
}
