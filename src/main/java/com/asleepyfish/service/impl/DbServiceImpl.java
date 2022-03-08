package com.asleepyfish.service.impl;

import com.asleepyfish.dto.ColumnTypeDTO;
import com.asleepyfish.enums.DataTypeEnum;
import com.asleepyfish.jdbc.MysqlConnector;
import com.asleepyfish.pojo.ConnParam;
import com.asleepyfish.service.DbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 13:57
 * @Description: TODO
 */
@Service
public class DbServiceImpl implements DbService {
    @Resource
    private ConnParam connParam;

    @Override
    public ColumnTypeDTO getColumnTypeMap(String tableName) {
        Map<String, String> map = new LinkedHashMap<>();
        MysqlConnector mysqlConnector = new MysqlConnector(connParam);
        String sql = "select COLUMN_NAME, data_type from information_schema.COLUMNS a where a.TABLE_NAME = ?";
        try {
            PreparedStatement preparedStatement = mysqlConnector.getConn().prepareStatement(sql);
            preparedStatement.setString(1, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // ResultSet获取返回值时，列名不区分大小写
                map.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map<String, String> javaMap = new LinkedHashMap<>();
        map.forEach((k, v) -> {
            String javaName = getJavaName(k);
            String javaType = DataTypeEnum.getJavaTypeBySqlType(v);
            javaMap.put(javaName, javaType);
        });
        ColumnTypeDTO columnTypeDTO = new ColumnTypeDTO();
        columnTypeDTO.setSqlMap(map);
        columnTypeDTO.setJavaMap(javaMap);
        return columnTypeDTO;
    }

    private String getJavaName(String sqlName) {
        String[] split = sqlName.split("_");
        StringBuilder javaName = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                javaName.append(split[i]);
            } else {
                javaName.append(split[i].substring(0, 1).toUpperCase()).append(split[i].substring(1));
            }
        }
        return javaName.toString();
    }
}
