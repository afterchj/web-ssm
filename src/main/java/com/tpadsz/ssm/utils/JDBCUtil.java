package com.tpadsz.ssm.utils;


import com.alibaba.fastjson.JSONObject;

import java.sql.*;

public class JDBCUtil {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
    private static String username = "root";
    private static String password = "123456";

    public static Connection conn;

    static {
        conn = getConnection();
    }

    /**
     * Description:获取数据连接<br/>
     *
     * @return yuanQiSheng
     * @throws Exception
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Description:释放数据库资源<br/>
     *
     * @param conn：Connection
     * @param pres：PreparedStatement
     * @param rs：ResultSet
     */
    public static void release(Connection conn, PreparedStatement pres, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (pres != null) {
                pres.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertMobile(JSONObject entity) {

        String sql = "insert into mobile_info(mobile,province,city,operater) values(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getString("mobile"));
            ps.setString(2, entity.getString("province"));
            ps.setString(3, entity.getString("city"));
            ps.setString(4, entity.getString("operater"));
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            release(conn, ps, null);
        }
    }

    public static void main(String[] args) {
        JSONObject entity = new JSONObject();
        entity.put("mobile", "18550791817");
        entity.put("province", "江苏");
        entity.put("city", "苏州");
        entity.put("operater", "联通");
        insertMobile(entity);
        System.out.println("conn =" + getConnection());
    }
}