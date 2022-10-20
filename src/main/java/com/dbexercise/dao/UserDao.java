//DB_HOST=jdbc:mysql://localhost:3306/likelion-db;DB_USER=root;DB_PASSWORD=baka
package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;


public class UserDao { //Dao (Data access object)
    private ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new LocalConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();
        PreparedStatement ps = connection.prepareStatement("delete from users");
        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection connection =connectionMaker.makeConnection();

        PreparedStatement ps = connection.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        connection.close();

        return count;
    }

    public User select(String id) throws SQLException, ClassNotFoundException { //서버 연결
        Connection connection = connectionMaker.makeConnection();
        // Query 문 작성
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        User user = null;
        if(rs.next()) {
            user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
        }

        rs.close();
        pstmt.close();
        connection.close();

        if(user == null) throw new EmptyResultDataAccessException(1);
        return user;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();

        PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO users(id, name, password) values(?,?,?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.add(new User("15", "won", "123456"));

        //  userDao.deleteAll();
        // System.out.println(user.getName());
    }
}
