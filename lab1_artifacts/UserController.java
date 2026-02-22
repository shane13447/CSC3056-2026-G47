package controller;

import java.util.Vector;

import model.User;

public class UserController {

	public static void loadUserData(Vector<User> users) {
		User aUser = new User("mike", "my_passwd", "Mike", "Smith", "07771234567");
		users.add(aUser);

		aUser = new User("james.cameron@gmail.com", "angel", "James", "Cameron", "07777654321");
		users.add(aUser);

		aUser = new User("julia.roberts@gmail.com", "change_me", "Julia", "roberts", "07770123456");
		users.add(aUser);
	}
}
