package com.asleepyfish.service.impl;

import com.asleepyfish.dto.ColumnTypeDTO;
import com.asleepyfish.enums.DataOperationEnum;
import com.asleepyfish.enums.ImportEnum;
import com.asleepyfish.param.GenerateEntityParam;
import com.asleepyfish.service.DbService;
import com.asleepyfish.service.GenerateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/28 17:04
 * @Description: 生成
 */
@Component
public class GenerateServiceImpl implements GenerateService {
    @Resource
    private DbService dbService;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private final Logger log = LoggerFactory.getLogger(GenerateServiceImpl.class);

    @Override
    public void generateEntity(GenerateEntityParam param) {
        String packagePath = param.getPackagePath();
        String className = param.getClassName();
        String authorName = param.getAuthorName();
        Map<String, String> entityMap;
        if (param.getTableName() != null) {
            entityMap = dbService.getColumnTypeMap(param.getTableName()).getJavaMap();
        } else {
            entityMap = param.getEntityMap();
        }
        if (!(packagePath.endsWith("/") || packagePath.endsWith("\\"))) {
            packagePath = packagePath.concat("/");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(packagePath + className + ".java"));
            // 生成包名
            generatePackage(packagePath, bw);
            // 引入包
            importPackage(entityMap, bw);
            // 生成作者信息
            generateAuthorInformation(bw, authorName);
            // 生成类名
            bw.write("public class " + className + " {\n");
            // 生成属性
            generateProperties(entityMap, bw);
            // 生成getter和setter
            generateGetterAndSetter(entityMap, bw);
            // 生成toString
            generateToString(className, entityMap, bw);
            bw.close();
        } catch (IOException e) {
            log.info("生成实体类失败，请检查实体类字段是否已在枚举类中有定义,错误信息:{}", e.getMessage());
        }
    }

    @Override
    public String generateMybatis(String tableName, DataOperationEnum dataOperationEnum) {
        ColumnTypeDTO columnTypeMap = dbService.getColumnTypeMap(tableName);
        Map<String, String> sqlMap = columnTypeMap.getSqlMap();
        Map<String, String> javaMap = columnTypeMap.getJavaMap();
        switch (dataOperationEnum) {
            case INSERT:
                return generateInsert(tableName, sqlMap, javaMap);
            case DELETE:

            case UPDATE:

            case SELECT:

            default:
                break;
        }
        return null;
    }

    /**
     * 生成包名
     *
     * @param packagePath
     * @param bw
     * @throws IOException
     */
    private void generatePackage(String packagePath, BufferedWriter bw) throws IOException {
        String[] split = packagePath.split("\\\\|/");
        StringBuilder packageName = new StringBuilder();
        int flag = 0;
        for (int i = split.length - 1; i >= 0; i--) {
            if (Objects.equals(split[i], "java")) {
                flag = i;
            }
        }
        for (int i = flag + 1; i < split.length - 1; i++) {
            packageName.append(split[i]).append(".");
        }
        packageName.append(split[split.length - 1]);
        // 包名
        bw.write("package " + packageName + ";\n\n");
        bw.flush();
    }

    /**
     * 引入包
     *
     * @param entityMap
     * @param bw
     * @throws IOException
     */
    private void importPackage(Map<String, String> entityMap, BufferedWriter bw) throws IOException {
        List<String> importPackageList = entityMap.values().stream().distinct().filter(this::judgeImport).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for (String importPackage : importPackageList) {
            String importName = ImportEnum.getImportNameByJavaType(importPackage);
            bw.write(importName + "\n");
            bw.flush();
        }
        bw.write("\n");
        bw.flush();
    }

    /**
     * 添加作者信息
     *
     * @param bw
     * @param authorName
     * @throws IOException
     */
    private void generateAuthorInformation(BufferedWriter bw, String authorName) throws IOException {
        bw.write("/**\n" +
                " * @Author: " + authorName + "\n" +
                " * @Date: " + FORMAT.format(new Date()) + "\n" +
                " * @Description: TODO\n" +
                " */\n");
        bw.flush();
    }

    /**
     * 判断是否为需要引入包的数据类型
     *
     * @param javaType
     * @return
     */
    private boolean judgeImport(String javaType) {
        return ImportEnum.getImportNameByJavaType(javaType) != null;
    }

    /**
     * 生成属性
     *
     * @param entityMap
     * @param bw
     * @throws IOException
     */
    private void generateProperties(Map<String, String> entityMap, BufferedWriter bw) throws IOException {
        for (Map.Entry<String, String> entry : entityMap.entrySet()) {
            bw.write("    private " + entry.getValue() + " " + entry.getKey() + ";\n\n");
            bw.flush();
        }
    }

    /**
     * 生成getter和setter
     *
     * @param entityMap
     * @param bw
     * @throws IOException
     */
    private void generateGetterAndSetter(Map<String, String> entityMap, BufferedWriter bw) throws IOException {
        for (Map.Entry<String, String> entry : entityMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            bw.write("    public " + value + " get" + key.substring(0, 1).toUpperCase() + key.substring(1) + "() {\n");
            bw.write("        return " + key + ";\n");
            bw.write("    }\n\n");
            bw.write("    public void set" + key.substring(0, 1).toUpperCase() + key.substring(1) + "(" + value + " " + key + ") {\n");
            bw.write("        this." + key + " = " + key + ";\n");
            bw.write("    }\n\n");
            bw.flush();
        }
    }

    /**
     * 生成toString
     *
     * @param className
     * @param entityMap
     * @param bw
     * @throws IOException
     */
    private void generateToString(String className, Map<String, String> entityMap, BufferedWriter bw) throws IOException {
        bw.write("    @Override\n");
        bw.write("    public String toString() {\n");
        bw.write("        return \"" + className + "{\" +\n");
        int i = 0;
        for (Map.Entry<String, String> entry : entityMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (i == 0) {
                if (Objects.equals(value, "String")) {
                    bw.write("                \"" + key + "='\" + " + key + " + '\\'' +\n");
                } else {
                    bw.write("                \"" + key + "=\" + " + key + " +\n");
                }
            } else {
                if (Objects.equals(value, "String")) {
                    bw.write("                \", " + key + "='\" + " + key + " + '\\'' +\n");
                } else {
                    bw.write("                \", " + key + "=\" + " + key + " +\n");
                }
            }
            i++;
        }
        bw.write("                '}';\n");
        bw.write("    }\n");
        bw.write("}");
        bw.flush();
    }

    /**
     * 生成insert
     *
     * @param tableName 表名
     * @param sqlMap    sql字段名称和类型
     * @param javaMap   java字段名称和类型
     * @return
     */
    private String generateInsert(String tableName, Map<String, String> sqlMap, Map<String, String> javaMap) {
        return "    <insert id=\"\" parameterType=\"java.util.List\">\n" +
                "        insert into " + tableName + "\n" +
                "        (" + concatSqlName(sqlMap) + ")\n" +
                "        values\n" +
                "        <foreach collection=\"list\" item=\"detail\" separator=\",\">\n" +
                "            (" + concatJavaName(javaMap) + ")\n" +
                "        </foreach>\n" +
                "    </insert>\n";
    }

    /**
     * 拼接javaName
     *
     * @param javaMap java字段名称和类型
     * @return
     */
    private String concatJavaName(Map<String, String> javaMap) {
        StringBuilder sb = new StringBuilder();
        // #{detail.sds},
        javaMap.forEach((k, v) -> sb.append("#{detail.").append(k).append("}, "));
        return sb.substring(0, sb.length() - 2);
    }

    /**
     * 拼接sqlName
     *
     * @param sqlMap sql字段名称和类型
     * @return
     */
    private String concatSqlName(Map<String, String> sqlMap) {
        StringBuilder sb = new StringBuilder();
        sqlMap.forEach((k, v) -> sb.append(k).append(", "));
        return sb.substring(0, sb.length() - 2);
    }
}
