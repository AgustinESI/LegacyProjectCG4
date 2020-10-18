package main.java.dao;

import main.java.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DAOUser implements main.java.dao.DAO {

    private Connection CONNECTION = null;

    public User create(User u) {
        CONNECTION = ConnectionSQLite.dbConnector();
        // TODO Auto-generated method stub
        return u;
    }

    public User read(User u) {
        CONNECTION = ConnectionSQLite.dbConnector();
        // TODO Auto-generated method stub
        return u;
    }

    public User delete(User u) {
        CONNECTION = ConnectionSQLite.dbConnector();
        // TODO Auto-generated method stub
        return u;
    }

    public User update(User u) {
        CONNECTION = ConnectionSQLite.dbConnector();
        // TODO Auto-generated method stub
        return u;
    }

    public List<User> selectAllUsers() {
        CONNECTION = ConnectionSQLite.dbConnector();

        Statement stmt = null;

        try {
            stmt = this.CONNECTION.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while (rs.next()){
                System.out.println(rs.getString("user_name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


}
