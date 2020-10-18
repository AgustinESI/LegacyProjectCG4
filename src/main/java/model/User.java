package main.java.model;

public class User {

	private String name;
	private String password;
	private String dni;

	public User(String name, String password, String dni) {
		super();
		this.name = name;
		this.password = password;
		this.dni = dni;
	}

	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", dni=" + dni + "]";
	}

}
