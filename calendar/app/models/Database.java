package models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Database {

	private static HashMap<String, User> userDatabase = new HashMap<String, User>();

	public static User getUser(String email) {
		return userDatabase.get(email);
	}

	public static void addUser(User user) {
		userDatabase.put(user.getUserMail(), user);
	}

	public static List<User> getAllUsers() {
		List<User> userList = new LinkedList<User>(userDatabase.values());

		return userList;
	}
}
