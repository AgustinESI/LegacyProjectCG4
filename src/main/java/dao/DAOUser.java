package main.java.dao;

import main.java.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		User aux = null;

		PreparedStatement ps;
		try {
			ps = this.CONNECTION.prepareStatement("select * from users where user_name = ? and user_pwd=?");

			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				aux = new User();
				aux.setDni(rs.getString("user_dni"));
				aux.setName(rs.getString("user_name"));
				aux.setPassword(rs.getString("user_pwd"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aux;
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
		List<User> list = new ArrayList<User>();
		Statement stmt = null;

		try {
			stmt = this.CONNECTION.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");

			while (rs.next()) {

				User u = new User();
				u.setDni(rs.getString("user_dni"));
				u.setName(rs.getString("user_name"));
				u.setPassword(rs.getString("user_pwd"));
				list.add(u);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return list;
	}

}
