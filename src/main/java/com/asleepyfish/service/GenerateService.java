package com.asleepyfish.service;

import com.asleepyfish.enums.DataOperationEnum;
import com.asleepyfish.param.GenerateEntityParam;
import org.springframework.stereotype.Service;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/28 17:03
 * @Description: 生成
 */
@Service
public interface GenerateService {
    /**
     * 生成实体类 入参
     *
     * @param param
     */
    void generateEntity(GenerateEntityParam param);

    String generateMybatis(String tableName, DataOperationEnum dataOperationEnum);
}
