package main.java.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import main.java.dao.DAOUser;
import main.java.model.User;

public class UserController {

	private DAOUser daoUser = new DAOUser();

	public User authenticateUser(String login, String password) {
		boolean authenticated = false;
		User u = null;
		if (StringUtils.isNotBlank(login) && StringUtils.isNotBlank(password)) {
			User aux = new User(login, password, null);
			u = this.daoUser.read(aux);
		}
		return u;
	}

	public boolean checkDNI(String arg) {
		return Pattern.compile("^[0-9]{8,8}[A-Za-z]$").matcher(arg).find();
	}

	public User createUser(String login, String password, String dni) {
		User aux = null;

		if (StringUtils.isNotBlank(login) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(dni)) {
			User u = new User(login, password, dni);
			if (this.daoUser.read(u) != null) {
				aux = (User) this.daoUser.create(u);
			}
		}
		return aux;
	}

	public boolean deleteUser(String login, String password) {
		boolean deleted = false;

		if (StringUtils.isNotBlank(login) && StringUtils.isNotBlank(password)) {
			User u = new User(login, password, null);
			if (this.daoUser.delete(u) != null) {
				deleted = true;
			}
		}
		return deleted;
	}

	public List<User> getAllUsers() {
		return this.daoUser.selectAllUsers();
	}

	public String getMessage(String name) {
		String chain = "";
		if (StringUtils.isNotBlank(name)) {
			InputStream inputStream = null;
			Properties prop = null;
			try {
				prop = new Properties();
				String propFileName = "messages.properties";

				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}

				chain = prop.getProperty(name);

				inputStream.close();
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}
		}
		return chain;
	}
}
