package com.asleepyfish.param;

import java.util.Map;

/**
 * @Author: zhoujh42045
 * @Date: 2022/3/8 10:02
 * @Description: 生成实体类入参，数据库表名传的话，自动根据数据库字段生成实体类，不传表名，根据map字段生成
 */
public class GenerateEntityParam {
    /**
     * 数据库表名
     */
    private String tableName;
    /**
     * 类名
     */
    private String className;

    /**
     * 实体属性和类型
     */
    private Map<String, String> entityMap;

    /**
     * 实体类存放的包路径
     */
    private String packagePath;

    /**
     * 作者名
     */
    private String authorName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, String> getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(Map<String, String> entityMap) {
        this.entityMap = entityMap;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "GenerateEntityParam{" +
                "tableName='" + tableName + '\'' +
                ", className='" + className + '\'' +
                ", entityMap=" + entityMap +
                ", packagePath='" + packagePath + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
