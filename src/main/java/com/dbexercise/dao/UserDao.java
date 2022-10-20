//DB_HOST=jdbc:mysql://localhost:3306/likelion-db;DB_USER=root;DB_PASSWORD=baka
package com.dbexercise.dao;
import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;


public class UserDao { //Dao (Data access object)
    private ConnectionMaker connectionMaker;
    public UserDao(){
        this.connectionMaker = new LocalConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User select(String id) throws SQLException, ClassNotFoundException { //서버 연결
        Connection connection = connectionMaker.makeConnection();
        // Query 문 작성
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users where id = ?");
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        pstmt.close();
        connection.close();
        return user;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();

        PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO users(id, name, password) values(?,?,?)");
        pstmt.setString(1,user.getId());
        pstmt.setString(2,user.getName());
        pstmt.setString(3,user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add(new User("4", "won", "123456"));

        // System.out.println(user.getName());
    }
}
