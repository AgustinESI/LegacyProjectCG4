package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOUser implements main.java.model.DAO {

	private Connection CONNECTION = null;

	public User create(User u) {
		CONNECTION = ConnectionSQLite.dbConnector();

		PreparedStatement ps;
		try {
			ps = this.CONNECTION.prepareStatement("INSERT INTO users (user_name, user_pwd, user_dni) VALUES (?, ?, ?);");

			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getDni());
			ps.executeUpdate();

			ps.close();
			CONNECTION.commit();
			CONNECTION.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
			rs.close();
			ps.close();
			CONNECTION.commit();
			CONNECTION.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aux;
	}

	public Boolean delete(User u) {
		CONNECTION = ConnectionSQLite.dbConnector();
		Boolean deleted = false;

		PreparedStatement ps;
		try {
			ps = this.CONNECTION.prepareStatement("delete from users where user_name=? and  user_pwd=?");

			ps.setString(1, u.getName());
			ps.setString(2, u.getPassword());

			ps.executeUpdate();
			deleted = true;
			ps.close();
			CONNECTION.commit();
			CONNECTION.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deleted;
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

			rs.close();
			stmt.close();
			CONNECTION.commit();
			CONNECTION.close();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return list;
	}

	public boolean readDNI(String dni) {
		CONNECTION = ConnectionSQLite.dbConnector();
		Boolean exist = false;
		User aux = null;
		PreparedStatement ps = null;
		try {
			ps = this.CONNECTION.prepareStatement("select * from users where user_dni = ?");
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				aux = new User();
				aux.setDni(rs.getString("user_dni"));
				aux.setName(rs.getString("user_name"));
				aux.setPassword(rs.getString("user_pwd"));
			}
			if (aux != null) {
				exist = true;
			}
			rs.close();
			ps.close();
			CONNECTION.commit();
			CONNECTION.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exist;
	}

}
