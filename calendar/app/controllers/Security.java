package controllers;

import models.Database;
import models.User;

public class Security extends Secure.Security {

	static boolean authenticate(String email, String password) {
		User user = Database.getUser(email);
		return user != null && user.password.equals(password);
	}

	/*
	 * static boolean check(String profile) { User user = User.find("byEmail",
	 * connected()).first(); if ("isAdmin".equals(profile)) { return
	 * user.isAdmin; } else { return false; } }
	 */
}