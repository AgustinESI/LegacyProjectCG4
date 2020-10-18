package main.java.dao;

import main.java.model.User;

public interface DAO {

	public Object create(User u);

	public Object read(User u);

	public Object update(User u);

	public Object delete(User u);

}
