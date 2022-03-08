package com.asleepyfish.enums;

import java.util.Objects;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 11:10
 * @Description: 数据库字段和Java实体类对应
 */
public enum DataTypeEnum {
    VARCHAR("varchar", "String"),
    CHAR("char", "String"),
    TEXT("text", "String"),
    TINYINT("tinyint", "Integer"),
    SMALLINT("smallint", "Integer"),
    MEDIUMINT("mediumint", "Integer"),
    INT("int", "Integer"),
    BIGINT("bigint", "Integer"),
    DECIMAL("decimal", "BigDecimal"),
    DATE("date", "Date"),
    TIME("time", "Time"),
    DATETIME("datetime", "Timestamp"),
    ENUM("enum", "String");
    /**
     * MySQL中的数据类型
     */
    private final String sqlType;

    /**
     * Java中的数据类型
     */
    private final String javaType;

    DataTypeEnum(String sqlType, String javaType) {
        this.sqlType = sqlType;
        this.javaType = javaType;
    }

    public String getSqlType() {
        return sqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public static String getJavaTypeBySqlType(String sqlType) {
        for (DataTypeEnum value : values()) {
            if (Objects.equals(value.getSqlType(), sqlType)) {
                return value.getJavaType();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "DataTypeEnum{" +
                "sqlType='" + sqlType + '\'' +
                ", javaType='" + javaType + '\'' +
                '}';
    }
}
