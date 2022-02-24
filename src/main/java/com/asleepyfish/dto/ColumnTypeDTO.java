package com.asleepyfish.dto;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 13:51
 * @Description: MySQL表中的列名和类型
 */
public class ColumnTypeDTO {
    /**
     * 列名
     */
    private String columnName;

    /**
     * 类型
     */
    private String columnType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Override
    public String toString() {
        return "ColumnTypeDTO{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                '}';
    }
}
