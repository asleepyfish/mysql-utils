package com.asleepyfish.jdbc;

import com.asleepyfish.pojo.ConnParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 9:15
 * @Description: 数据库连接
 */
public class MysqlConnector {
    private Connection conn;

    public MysqlConnector(ConnParam connParam) {
        try {
            // 注册 JDBC 驱动
            Class.forName(connParam.getDriver());
            // 使用DriverManager获取连接
            long start = System.currentTimeMillis();
            conn = DriverManager.getConnection(connParam.getUrl(), connParam.getUser(), connParam.getPassword());
            long end = System.currentTimeMillis();
            System.out.println("共耗时：" + (end - start) + "ms");
            // 设置是否自动提交
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    /**
     * 提交事务
     */
    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
