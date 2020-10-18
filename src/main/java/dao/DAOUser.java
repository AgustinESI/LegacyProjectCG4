package main.java.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import main.java.model.User;

public class DAOUser implements main.java.dao.DAO {

	private Connection CONNECTION = null;

	public Object create(User u) {
		this.openConnection();
		// TODO Auto-generated method stub
		return u;
	}

	public Object read(User u) {
		this.openConnection();
		// TODO Auto-generated method stub
		return u;
	}

	public Object delete(User u) {
		this.openConnection();
		// TODO Auto-generated method stub
		return u;
	}

	public Object update(User u) {
		this.openConnection();
		// TODO Auto-generated method stub
		return u;
	}

	public void openConnection() {
		InputStream inputStream = null;
		Properties prop = null;
		try {
			prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			//TODO: hacer la conexion
			
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

}
