package com.example.ecommerce.dao;

import java.sql.*;

public class DB {
    private static  Connection connection;
    public static Connection getConnection(){
        if(connection==null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection  = DriverManager.getConnection("Jdbc:mysql://localhost:3307/ecommerce", "root", "0000");
//                Statement st = connection.createStatement();
//                String query = "select * from compte";
//                ResultSet rs = st.executeQuery(query);
//                while (rs.next()) {
//                    System.out.println(rs.getString(1) + " " + rs.getString(2));
//                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
