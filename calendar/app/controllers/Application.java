package controllers;

import java.util.List;

import models.Calendar;
import models.Database;
import models.Event;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Application extends Controller {

	@Check("isAdmin")
	public static void delete(Long id) {

	}

	public static void index() {
		User user = Database.getUser(Security.connected());
		List users = Database.getAllUsers();
		// Remove the current connected user
		users.remove(user);
		List calendars = user.getCalendarList();
		render(calendars, user, users);
	}

	public static void showCalendars(String email) {
		User user = Database.getUser(email);
		List calendars = user.getCalendarList();
		render(user, calendars);
	}

	public static void showEvents(String email, String calendarname) {
		User user = Database.getUser(email);
		List users = Database.getAllUsers();
		Calendar calendar = user.getCalendarByName(calendarname);
		List events = calendar.getEventsAsList();
		render(user, calendar, events);
	}

	public static void addEvent(String eventName, String calendarname,
			String email, String startsAt, String endsAt, String isPublic) {
		User user = Database.getUser(email);
		Calendar calendar = user.getCalendarByName(calendarname);
		// Wenn es nicht der gleiche User ist, kann er keine Events adden.
		if (email.equals(Security.connected())) {
			boolean fuerAlleSichtbar = true;
			if (isPublic == null)
				fuerAlleSichtbar = false;

			calendar.addEvent(new Event(eventName, startsAt, endsAt,
					fuerAlleSichtbar));
		}

		showEvents(user.getUserMail(), calendar.name);
	}

}