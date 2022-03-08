package com.asleepyfish.dto;

import java.util.Map;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 13:51
 * @Description: MySQL表中的列名和类型
 */
public class ColumnTypeDTO {
    /**
     * 数据库中的字段名和类型
     */
    Map<String, String> sqlMap;

    /**
     * java中对应的字段名和类型
     */
    Map<String, String> javaMap;

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    public Map<String, String> getJavaMap() {
        return javaMap;
    }

    public void setJavaMap(Map<String, String> javaMap) {
        this.javaMap = javaMap;
    }

    @Override
    public String toString() {
        return "ColumnTypeDTO{" +
                "sqlMap=" + sqlMap +
                ", javaMap=" + javaMap +
                '}';
    }
}
