package com.example.demo.config;

import com.alibaba.druid.util.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    public DataSource defaultTargetDataSource=null;
    public Map<Object, Object> targetDataSources = new HashMap<>();
    public static final ThreadLocal<String> dynamicDataSourceHandler = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        this.defaultTargetDataSource = defaultTargetDataSource;
        this.targetDataSources = targetDataSources;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        if (StringUtils.isEmpty(dynamicDataSourceHandler.get())){
            dynamicDataSourceHandler.set("master");
        }
        return dynamicDataSourceHandler.get();
    }

    @Override
    public void afterPropertiesSet() {
        //设置目标数据源
        super.setTargetDataSources(targetDataSources);
        //设置默认数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.afterPropertiesSet();
    }
}
