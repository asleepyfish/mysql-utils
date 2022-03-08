package com.asleepyfish;

import com.asleepyfish.enums.DataOperationEnum;
import com.asleepyfish.param.GenerateEntityParam;
import com.asleepyfish.service.GenerateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/28 17:18
 * @Description: TODO
 */
@SpringBootTest
public class GenerateTest {
    @Resource
    private GenerateService generateService;

    @Test
    public void testGenerateEntity() {
        GenerateEntityParam generateEntityParam = new GenerateEntityParam();
        String packagePath = "D:\\Code\\Java\\study\\mysql-utils\\src\\main\\java\\com\\asleepyfish\\pojo";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("port", "String");
        map.put("port1", "String");
        map.put("port2", "BigDecimal");
        map.put("port3", "Time");
        map.put("port4", "Date");
        map.put("port6", "Timestamp");
        map.put("port5", "Time");
        map.put("port7", "Long");
        generateEntityParam.setTableName(null);
        generateEntityParam.setClassName("AbcYt");
        generateEntityParam.setEntityMap(map);
        generateEntityParam.setPackagePath(packagePath);
        generateEntityParam.setAuthorName("zhoujh");
        generateService.generateEntity(generateEntityParam);
    }

    @Test
    public void testGenerateMybatis() {
        String tableName = "a_security_fundnav";
        DataOperationEnum dataOperationEnum = DataOperationEnum.INSERT;
        System.out.println(generateService.generateMybatis(tableName, dataOperationEnum));
    }
}