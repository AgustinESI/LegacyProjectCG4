package test.java;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.Test;

import main.java.controller.UserController;
import main.java.model.User;

public class UserTests {

	UserController userController = new UserController();

	@Test
	public void createUser() {
		User user = new User("Prueba", "Prueba", "12356879F");

		User aux = userController.createUser(user);

		assertEquals(aux.getName(), user.getName());
		assertEquals(aux.getPassword(), user.getPassword());
		assertEquals(aux.getDni(), user.getDni());

	}
}
