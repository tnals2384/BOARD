package org.example.springBoard.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/board";
    public static final String user = "root";
    public static final String password = "11111111";

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            System.out.println("커넥션 생성 오류");
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables){
        for(AutoCloseable closeable : closeables){
            if(closeable != null){
                try {
                    closeable.close();
                } catch (Exception e) {
                    System.out.println("close 하다가 에러나는거 아직까지 못보긴 했음.");
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
