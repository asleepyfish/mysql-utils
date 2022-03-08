package com.asleepyfish.enums;

/**
 * @Author: zhoujh42045
 * @Date: 2022/3/8 13:34
 * @Description: 数据操作
 */
public enum DataOperationEnum {
    INSERT(1, "insert"),
    DELETE(2, "delete"),
    UPDATE(3, "update"),
    SELECT(4, "select");

    private final Integer value;
    private final String name;

    DataOperationEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DataOperationEnum{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
