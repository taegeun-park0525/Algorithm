package com.dbexercise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class LocalConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        Map<String,String> env = System.getenv();
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/likelion-db", "root", "baka");

        return connection;
    }

}
