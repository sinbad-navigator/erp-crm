package com.ec.auth.datasource;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源切换处理
 *
 * @author xxxx
 */

@Slf4j
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> db = new ThreadLocal<>();

    public static String getDataSourceKey() {
        return db.get();
    }

    public static void setDataSourceKey(String key) {
        db.set(key);
    }

    public static void clearDataSourceKey() {
        db.remove();
    }
}
