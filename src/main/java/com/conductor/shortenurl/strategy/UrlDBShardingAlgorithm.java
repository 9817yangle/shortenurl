package com.conductor.shortenurl.strategy;

import com.conductor.shortenurl.context.UserContextHolder;
import com.conductor.shortenurl.util.ShardingUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author enping
 * @date 2023/4/22 17:29
 * 短链接分库分表路由策略
 **/
public class UrlDBShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    /**
     * @param databaseNames        数据源集合 在分库时值为所有分片库的集合 databaseNames ds0 - ds4
     * @param preciseShardingValue 分片属性，包括
     *                             logicTableName 为逻辑表，
     *                             columnName 分片健（字段），
     *                             value 为从 SQL 中解析出的分片健的值
     */
    @Override

    public String doSharding(Collection<String> databaseNames, PreciseShardingValue<String> preciseShardingValue) {
        long hashCode = UserContextHolder.holder.get();//获得hash
        long slot = Math.abs(hashCode % ShardingUtil.SUM_SLOT);//分片序号
        for (String databaseName : databaseNames) {
            if (databaseName.endsWith(String.valueOf(slot / ShardingUtil.TBL_CNT))) {
                return databaseName;
            }
        }
        return null;
    }
}
