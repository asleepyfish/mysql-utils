package com.asleepyfish.service;

import com.asleepyfish.dto.ColumnTypeDTO;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 13:49
 * @Description: TODO
 */
public interface DbService {
    /**
     * 获取数据库对应表的字段名和类型名
     *
     * @param tableName 表名
     * @return
     */
    ColumnTypeDTO getColumnTypeMap(String tableName);
}
