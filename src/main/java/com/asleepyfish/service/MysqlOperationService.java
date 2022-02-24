package com.asleepyfish.service;

import com.asleepyfish.dto.ColumnTypeDTO;

import java.util.List;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 13:49
 * @Description: TODO
 */
public interface MysqlOperationService {
    List<ColumnTypeDTO> getColumnTypeList(String tableName);
}
