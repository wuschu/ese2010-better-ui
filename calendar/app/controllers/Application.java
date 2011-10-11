package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import models.Calendar;
import models.Database;
import models.Day;
import models.Event;
import models.User;

import org.joda.time.DateTime;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Application extends Controller {
	public static DateFormat formatter = new SimpleDateFormat(
			"dd.MM.yyyy, HH:mm");
	public static final String[] months = { "Januar", "Februar", "März",
			"April", "Mai", "Juni", "Juli", "August", "September", "Oktober",
			"November", "December" };

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

	public static void showEvents(String email, String calendarname,
			String message) {
		User user = Database.getUser(email);
		User connectedUser = Database.getUser(Security.connected());
		Calendar calendar = user.getCalendarByName(calendarname);
		List events = connectedUser.getEventsAllowedToSee(calendar, new Date());
		// Creating a list of day contained in a month
		String month = months[new DateTime().getMonthOfYear() - 1];
		List<Day> days = new LinkedList<Day>();
		java.util.Calendar displayedCalendar = java.util.Calendar.getInstance();
		for (int i = 1; i <= displayedCalendar
				.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); i++) {
			days.add(new Day(i));
		}
		render(user, calendar, events, connectedUser, month, days, message);
	}

	public static void addEvent(String eventName, String calendarname,
			String email, String startsAt, String endsAt, String isPublic) {
		String message = null;
		User user = Database.getUser(email);
		Calendar calendar = user.getCalendarByName(calendarname);
		// For input controll, so that the programm doesn't crash
		try {
			Date startTime = formatter.parse(startsAt);
			Date endTime = formatter.parse(endsAt);
		} catch (Exception e) {
			message = "Invalid Input!!!! du huärrä ARSCHLOCH chasch nid läse!!!";
			showEvents(user.getUserMail(), calendar.name, message);
		}

		// Wenn es nicht der gleiche User ist, kann er keine Events adden.
		if (email.equals(Security.connected())) {
			boolean fuerAlleSichtbar = true;
			if (isPublic == null)
				fuerAlleSichtbar = false;
			calendar.addEvent(new Event(eventName, startsAt, endsAt,
					fuerAlleSichtbar));
		}

		showEvents(user.getUserMail(), calendar.name, message);
	}
}