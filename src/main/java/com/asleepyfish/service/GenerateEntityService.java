package com.asleepyfish.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/21 14:40
 * @Description: 生成实体类和mybatis增删改查语句
 */
public class GenerateEntityService {
    private static String filePath = "src/main/java/";

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public static void main(String[] args) throws IOException {
        System.out.println("请输入实体类所在包名：");
        Scanner sc = new Scanner(System.in);
        String packageName = sc.next();
        String packagePath = packageName.replaceAll("\\.", "\\/");
        filePath = filePath + packagePath + "/";
        System.out.println("请输入类名：");
        String className = sc.next();
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath + className + ".java"));
        bw.write("package " + packageName + ";\n\n");
        bw.write(generateDescription());
        bw.write("public class " + className + " {\n");
        bw.flush();
        System.out.println("请输入属性名，中间以,隔开");
        String text = sc.next();
        String[] filedNames = text.split(",");
        String concat = "";
        for (int i = 0; i < filedNames.length; i++) {
            if (filedNames[i].contains("_")) {
                String[] s = filedNames[i].split("_");
                concat = s[0].concat(s[1].substring(0, 1).toUpperCase()).concat(s[1].substring(1));
            } else {
                concat = filedNames[i];
            }
            if (i != filedNames.length - 1) {
                bw.write(generateAttributes(concat));
                bw.newLine();
                bw.flush();
            }
        }
        bw.write(generateAttributes(concat));
        bw.flush();
        bw.write("}");
        bw.flush();
        bw.close();
        System.out.println("请输入需要生成的mybatis语句的序号：");
        System.out.println("1.insert");
        System.out.println("2.delete");
        System.out.println("3.update");
        System.out.println("4.select");
        int number = sc.nextInt();
        switch (number) {
            case 1:
                System.out.println(generateInsert(text));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    private static String generateAttributes(String name) {
        return "    private String " + name + ";\n";
    }

    private static String generateDescription() {
        return "/**\n" +
                " * @Author: zhoujh42045\n" +
                " * @Date: " + formatter.format(new Date()) + "\n" +
                " * @Description: TODO\n" +
                " */\n";
    }

    private static String generateInsert(String text) {
        String detail = generateForeachDetail(text);
        text = text.replaceAll(",", ", ");
        return "    <insert id=\"\" parameterType=\"java.util.List\">\n" +
                "        insert into 表名\n" +
                "        (" + text + ")\n" +
                "        values\n" +
                "        <foreach collection=\"list\" item=\"detail\" separator=\",\">\n" +
                "            (" + detail + ")\n" +
                "        </foreach>\n" +
                "    </insert>\n";
    }

    private static String generateForeachDetail(String text) {
        String[] element = text.split(",");
        StringBuilder sb = new StringBuilder();
        String concat = "";
        for (int i = 0; i < element.length; i++) {
            if (element[i].contains("_")) {
                String[] part = element[i].split("_");
                concat = part[0].concat(part[1].substring(0, 1).toUpperCase()).concat(part[1].substring(1));
            } else {
                concat = element[i];
            }
            if (i != element.length - 1) {
                sb.append("#{detail.").append(concat).append("}, ");
            }
        }
        sb.append("#{detail.").append(concat).append("}");
        return sb.toString();
    }
}
