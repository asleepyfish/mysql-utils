package com.asleepyfish.service.impl;

import com.asleepyfish.dto.ColumnTypeDTO;
import com.asleepyfish.jdbc.MysqlConnector;
import com.asleepyfish.pojo.ConnParam;
import com.asleepyfish.service.MysqlOperationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 13:57
 * @Description: TODO
 */
@Component
public class MysqlOperationServiceImpl implements MysqlOperationService {
    @Resource
    private ConnParam connParam;

    @Override
    public List<ColumnTypeDTO> getColumnTypeList(String tableName) {
        MysqlConnector mysqlConnector = new MysqlConnector(connParam);
        String sql = "select COLUMN_NAME, data_type from information_schema.COLUMNS a where a.TABLE_NAME = ?";
        try {
            PreparedStatement preparedStatement = mysqlConnector.conn.prepareStatement(sql);
            preparedStatement.setString(1, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // ResultSet获取返回值时，列名不区分大小写
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
