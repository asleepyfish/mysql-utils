package com.asleepyfish;

import com.asleepyfish.jdbc.MysqlConnector;
import com.asleepyfish.pojo.ConnParam;
import com.asleepyfish.service.MysqlOperationService;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 9:42
 * @Description: TODO
 */
@SpringBootTest
public class ConnectionTest {
    @Resource
    private MysqlOperationService mysqlOperationService;

    @Test
    public void testConnection() {
        mysqlOperationService.getColumnTypeList("a_security");
    }
}
