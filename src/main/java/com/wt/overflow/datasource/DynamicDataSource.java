package com.wt.overflow.datasource;

import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 暂时废弃
 */
@Order(2)
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("当前数据源为"+DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();

    }
}
