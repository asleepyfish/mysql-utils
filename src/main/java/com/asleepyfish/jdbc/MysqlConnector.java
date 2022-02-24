package com.asleepyfish.jdbc;

import com.asleepyfish.pojo.ConnParam;

import java.sql.*;

/**
 * @Author: zhoujh42045
 * @Date: 2022/2/24 9:15
 * @Description: 数据库连接
 */
public class MysqlConnector {
    public Connection conn;

    public Statement statement;

    public MysqlConnector(ConnParam connParam) {
        try {
            // 注册 JDBC 驱动
            Class.forName(connParam.getDriver());
            // 使用DriverManager获取连接
            conn = DriverManager.getConnection(connParam.getUrl(), connParam.getUser(), connParam.getPassword());
            // 设置是否自动提交
            conn.setAutoCommit(false);
            // 创建statement类对象，用来执行SQL语句
            statement = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
