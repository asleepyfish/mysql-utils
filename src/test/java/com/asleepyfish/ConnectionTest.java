package com.asleepyfish;

import com.asleepyfish.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 9:42
 * @Description: TODO
 */
@SpringBootTest
public class ConnectionTest {
    @Resource
    private DbService mysqlOperationService;

    @Test
    public void testConnection() {
        Map<String, String> map = mysqlOperationService.getColumnTypeMap("d_portfolio").getJavaMap();
        map.entrySet().forEach(System.out::println);
    }
}
