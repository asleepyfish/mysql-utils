package com.asleepyfish.enums;

import java.util.Objects;

/**
 * @Author: zhoujh42045
 * @Date: 2022/3/3 13:21
 * @Description: 需要引入包的JavaType
 */
public enum ImportEnum {
    BIG_DECIMAL("BigDecimal", "import java.math.BigDecimal;"),
    DATE("Date", "import java.sql.Date;"),
    TIME("Time", "import java.sql.Time;"),
    TIMESTAMP("Timestamp", "import java.sql.Timestamp;");
    private final String javaType;

    private final String importName;

    ImportEnum(String javaType, String importName) {
        this.javaType = javaType;
        this.importName = importName;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getImportName() {
        return importName;
    }

    public static String getImportNameByJavaType(String javaType) {
        for (ImportEnum value : values()) {
            if (Objects.equals(value.javaType, javaType)) {
                return value.getImportName();
            }
        }
        return null;
    }
}
