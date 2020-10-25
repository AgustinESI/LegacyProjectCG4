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

		
		User a1 = new User("p1", "p1","12356879F");
		
		aux = userController.createUser(a1);

		assertEquals(aux.getName(), a1.getName());
		assertEquals(aux.getPassword(), a1.getPassword());
		assertEquals(aux.getDni(), a1.getDni());
		
		
		User a2 = new User("p2", "p2","12356870F");
		
		aux = userController.createUser(a2);

		assertEquals(aux.getName(), a2.getName());
		assertEquals(aux.getPassword(), a2.getPassword());
		assertEquals(aux.getDni(), a2.getDni());
		
		User a3 = new User("p3", "p3","12356878F");
		
		aux = userController.createUser(a3);

		assertEquals(aux.getName(), a3.getName());
		assertEquals(aux.getPassword(), a3.getPassword());
		assertEquals(aux.getDni(), a3.getDni());
		
		User a4 = new User("p4", "p4","12356877F");
		
		aux = userController.createUser(a4);

		assertEquals(aux.getName(), a4.getName());
		assertEquals(aux.getPassword(), a4.getPassword());
		assertEquals(aux.getDni(), a4.getDni());
		
		User a5 = new User("p5", "p5","12356875F");
		
		aux = userController.createUser(a5);

		assertEquals(aux.getName(), a5.getName());
		assertEquals(aux.getPassword(), a5.getPassword());
		assertEquals(aux.getDni(), a5.getDni());
		
	}
}
